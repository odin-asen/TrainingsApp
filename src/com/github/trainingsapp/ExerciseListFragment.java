package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

import java.util.List;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 27.06.13
 */
public class ExerciseListFragment extends Fragment {
  private ArrayAdapter<Exercise> mExercises;

  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.exercise_list, container);
  }

  public void setExercises(List<Exercise> exercises) {
    mExercises = new ArrayAdapter<Exercise>(getActivity(),
        android.R.layout.simple_list_item_1, exercises);
    final ListView listView = (ListView) getActivity().findViewById(R.id.list_view);
    listView.setAdapter(mExercises);
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
