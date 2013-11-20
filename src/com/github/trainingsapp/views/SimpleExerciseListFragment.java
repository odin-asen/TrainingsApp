package com.github.trainingsapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.R;
import com.github.trainingsapp.business.Converter;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.data.DatabaseAccessor;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 20.11.13
 */
public class SimpleExerciseListFragment extends Fragment {
  private DatabaseAccessor mDBAccessor;
  private ListView.OnItemClickListener mSimpleItemListener;

  public static int ORDER_BY_NOTHING = -1;
  public static int ORDER_BY_NAME = 0;
  public static int ORDER_BY_MUSCLE = 1;
  private int mOrder;

  /****************/
  /* Constructors */

  public SimpleExerciseListFragment() {
    mOrder = ORDER_BY_NOTHING;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  /** Activity muss ListView.OnItemClickListener implementieren. */
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (activity instanceof ListView.OnItemClickListener) {
      mSimpleItemListener = (ListView.OnItemClickListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implement ListView.OnItemClickListener");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.simple_exercise_list, container, false);
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
   * Holt die Uebungen aus der Datenbank, unabhaengig von der Sortierungseinstellung.
   * mDBAccessor.open() muss vorher aufgerufen werden, wenn nicht schon getan.
   */
  private List<DTOExercise> getAllExercises() {
    List<DTOExercise> exercises = mDBAccessor.getAllExercises();
    mDBAccessor.fillListObjects(exercises);

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
    ((ListView) activity.findViewById(R.id.simple_list_view))
        .setOnItemClickListener(mSimpleItemListener);

    /* ActionBar Titel aendern */
    activity.getActionBar().setTitle(getString(R.string.app_name));
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
      ArrayAdapter<Exercise> adapter = new SimpleExerciseArrayAdapter(getActivity(), exercises);
      ((ListView) getActivity().findViewById(R.id.simple_list_view)).setAdapter(adapter);
    }
  }

  /*        End        */
  /*********************/
}
