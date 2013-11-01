package com.github.trainingsapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.github.R;
import com.github.trainingsapp.business.Converter;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.data.DatabaseAccessor;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.*;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 27.06.13
 */
public class ExerciseListFragment extends Fragment {
  private DatabaseAccessor mDBAccessor;
  private AdapterView.OnItemClickListener mItemListener;

  public static int ORDER_BY_NOTHING = -1;
  public static int ORDER_BY_NAME = 0;
  public static int ORDER_BY_MUSCLE = 1;
  private int mOrder;

  /****************/
  /* Constructors */

  public ExerciseListFragment() {
    mOrder = ORDER_BY_NOTHING;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  /** Activity muss AdapterView.OnItemClickListener implementieren. */
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (activity instanceof AdapterView.OnItemClickListener) {
      mItemListener = (AdapterView.OnItemClickListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implemenet AdapterView.OnItemClickListener");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.exercise_list, container, false);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mDBAccessor = new DatabaseAccessor(getActivity());
  }

  @Override
  public void onResume() {
    refresh();
    super.onResume();
  }

  /**
   * Holt die Uebungen aus der Datenbank, abhaengig von der Sortierungseinstellung.
   * mDBAccessor.open() muss vorher aufgerufen werden, wenn nicht schon getan.
   */
  private List<DTOExercise> getAllExercises() {
    List<DTOExercise> exercises = mDBAccessor.getAllExercises();
    mDBAccessor.fillListObjects(exercises);

    if(mOrder == ORDER_BY_NAME) {
      Collections.sort(exercises, new NameComparator());
    } else if(mOrder == ORDER_BY_MUSCLE) {
      Collections.sort(exercises, new MuscleComparator());
    }

    return exercises;
  }

  @Override
  public void onPause() {
    mDBAccessor.close();
    super.onPause();
  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */

  private void refresh() {
    final Activity activity = getActivity();

    /* Datenbank oeffnen und Exerciseliste holen. */
    mDBAccessor.open();
    Converter converter = new Converter(activity);
    final List<DTOExercise> dtos = getAllExercises();
    final List<Exercise> values = new ArrayList<Exercise>(dtos.size());
    for (DTOExercise dto : dtos) {
      values.add(converter.fromDTO(dto));
    }

    /* Exerciseliste und OnItemClickListener setzen */
    setExercises(values);
    ((ListView) activity.findViewById(R.id.list_view))
        .setOnItemClickListener(mItemListener);

    /* ActionBar Titel aendern */
    activity.getActionBar().setTitle(getString(R.string.app_name));
  }

  private List<String> getAlphabeticalGroup(List<Exercise> exercises) {
    /* Nach Namen sortierte Liste */
    Collections.sort(exercises, new Comparator<Exercise>() {
      @Override
      public int compare(Exercise lhs, Exercise rhs) {
        return lhs.getName().compareTo(rhs.getName());
      }
    });

    /* Erstelle eine Liste mit den ersten Buchstaben des Alphabets,
     * die auch erster Buchstabe in den Uebungen sind. Jeder Buchstabe kommt
     * in der Liste nur einmal vor. */
    final List<String> alphabeticalGroup = new ArrayList<String>();
    String firstCharacter = exercises.get(0).getName().substring(0,1);
    alphabeticalGroup.add(firstCharacter);

    for (Exercise exercise : exercises) {
      final String currentFirstCharacter = exercise.getName().substring(0,1);
      if(!firstCharacter.equals(currentFirstCharacter)) {
        firstCharacter = currentFirstCharacter;
        alphabeticalGroup.add(firstCharacter);
      }
    }

    return alphabeticalGroup;
  }

  private Map<String, List<Exercise>> getAlphabeticalExercises(List<String> group, List<Exercise> exercises) {
    /* Nach Namen sortierte Liste */
    Collections.sort(exercises, new Comparator<Exercise>() {
      @Override
      public int compare(Exercise lhs, Exercise rhs) {
        return lhs.getName().compareTo(rhs.getName());
      }
    });

    /* Erstelle eine Map, die Listen mit Uebungen enthaelt. Uebungen mit gleichen
     * Anfangsbuchstaben sind in einer Liste. */
    final Map<String, List<Exercise>> alphabeticalExercises = new ArrayMap<String, List<Exercise>>(group.size());
    /* Gruppen initialisieren */
    for (String s : group) {
      alphabeticalExercises.put(s, new ArrayList<Exercise>(2));
    }

    /* Listen fuellen */
    for (Exercise exercise : exercises) {
      final String firstCharacter = exercise.getName().substring(0,1);
      alphabeticalExercises.get(firstCharacter).add(exercise);
    }

    return alphabeticalExercises;
   }

  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */

  /**
   * Setzt den Sortierwert und aktualisiert die Liste.
   * @param orderBy Eine der ORDER_BY-Konstanten.
   */
  public void setOrder(int orderBy) {
    if(orderBy != mOrder) {
      mOrder = orderBy;
      refresh();
    }
  }

  /** Setzt Exercise-Objekte in die Liste. */
  public void setExercises(List<Exercise> exercises) {
    if(getActivity() != null) {
      List<String> alphabeticalOrder = getAlphabeticalGroup(exercises);
      Map<String, List<Exercise>> alphabeticalExercise = getAlphabeticalExercises(alphabeticalOrder, exercises);
      ExpandableListAdapter adapter = new ExerciseArrayAdapter(getActivity(),
          alphabeticalOrder, alphabeticalExercise);
      ((ExpandableListView) getActivity().findViewById(R.id.list_view)).setAdapter(adapter);
    }
  }

  /*        End        */
  /*********************/

  /*****************/
  /* Inner classes */

  private class NameComparator implements Comparator<DTOExercise> {
    @Override
    public int compare(DTOExercise object, DTOExercise object1) {
      return object.name.compareTo(object1.name);
    }
  }

  private class MuscleComparator implements Comparator<DTOExercise> {
    private final int RESULT_EQUAL = 0;

    @Override
    public int compare(DTOExercise object, DTOExercise object1) {
      /* Vergleiche Primaermuskeln */
      final int compareResult = compareStringArrays(object.primaryMuscles, object1.primaryMuscles);
      if(compareResult == RESULT_EQUAL) {
        /* Vergleiche Sekundaermuskeln */
        return compareStringArrays(object.secondaryMuscles, object1.secondaryMuscles);
      } else {
        if(compareResult > RESULT_EQUAL)
          return 1;
        else return -1;
      }
    }

    private int compareStringArrays(String[] thisArray, String[] thatArray) {
      for (String thisString : thisArray) {
        for (String thatString : thatArray) {
          final int compareResult = thisString.compareTo(thatString);
          if(compareResult > RESULT_EQUAL)
            return 1;
          else if(compareResult < RESULT_EQUAL)
            return -1;
        }
      }

      return RESULT_EQUAL;
    }
  }
  /*      End      */
  /*****************/
}
