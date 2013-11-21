package com.github.trainingsapp.views;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

import java.util.List;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 20.11.13
 */
public class SimpleListFragment extends ExerciseListFragment {
  private ListView.OnItemClickListener mItemClickListener;

  /****************/
  /* Constructors */

  public SimpleListFragment() {
    super(R.layout.simple_exercise_list);
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
      mItemClickListener = (ListView.OnItemClickListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implement ListView.OnItemClickListener");
    }
  }

  public void refresh() {
    super.refresh();

    FragmentActivity activity = getActivity();

    ((ListView) activity.findViewById(R.id.simple_list_view))
        .setOnItemClickListener(mItemClickListener);

    /* ActionBar Titel aendern */
    activity.getActionBar().setTitle(getString(R.string.app_name));
  }

  /*   End   */
  /***********/

  /*********************/
  /* Getter and Setter */

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
