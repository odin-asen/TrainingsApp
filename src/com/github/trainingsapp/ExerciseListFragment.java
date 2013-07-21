package com.github.trainingsapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * Date: 27.06.13
 */
public class ExerciseListFragment extends Fragment {
  private DatabaseAccessor dbAccessor;
  private AdapterView.OnItemClickListener mItemListener;

  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  /**
   * Activity muss AdapterView.OnItemClickListener implementieren.
   */
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

  public void setExercises(List<Exercise> exercises) {
    if(getActivity() != null) {
      ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(getActivity(),
          android.R.layout.simple_list_item_1, exercises);
      ((ListView) getActivity().findViewById(R.id.list_view)).setAdapter(adapter);
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    dbAccessor = new DatabaseAccessor(getActivity());
  }

  @Override
  public void onResume() {
    final Activity activity = getActivity();

    dbAccessor.open();
    Converter converter = new Converter(activity);
    final List<DTOExercise> dtos = dbAccessor.getAllExercises();
    dbAccessor.fillListObjects(dtos);
    final List<Exercise> values = new ArrayList<Exercise>(dtos.size());
    for (DTOExercise dto : dtos) {
      values.add(converter.fromDTO(dto));
    }
    setExercises(values);
    ((ListView) activity.findViewById(R.id.list_view))
        .setOnItemClickListener(mItemListener);

    super.onResume();
  }

  @Override
  public void onPause() {
    dbAccessor.close();
    super.onPause();
  }

  @Override
  public void onStart() {
    super.onStart();

  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */
  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */
  /*        End        */
  /*********************/

  /*****************/
  /* Inner classes */
  /*      End      */
  /*****************/
}
