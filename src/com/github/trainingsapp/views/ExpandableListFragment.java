package com.github.trainingsapp.views;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.business.container.CategoryContainer;
import com.github.trainingsapp.business.container.ExerciseMuscleContainer;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.List;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 27.06.13
 */
public class ExpandableListFragment extends ExerciseListFragment {
  private ExpandableListView.OnChildClickListener mClickListener;

  /****************/
  /* Constructors */

  public ExpandableListFragment() {
    super(R.layout.exercise_list);
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  /** Activity muss ExpandableListView.OnChildClickListener implementieren. */
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (activity instanceof ExpandableListView.OnChildClickListener) {
      mClickListener = (ExpandableListView.OnChildClickListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implement ExpandableListView.OnChildClickListener");
    }
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

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */
  public void refresh() {
    super.refresh();

    final FragmentActivity activity = getActivity();

    ((ExpandableListView) activity.findViewById(R.id.list_view))
        .setOnChildClickListener(mClickListener);

    /* ActionBar Titel aendern */
    activity.getActionBar().setTitle(getString(R.string.app_name));
  }

  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */

  /** Setzt Exercise-Objekte in die Liste. */
  public void setExercises(List<Exercise> exercises) {
    if(getActivity() != null) {
      final CategoryContainer<String,Exercise> container = getContainer(exercises);

      ExpandableListAdapter adapter = new ExerciseArrayAdapter(getActivity(), container);
      ((ExpandableListView) getActivity().findViewById(R.id.list_view)).setAdapter(adapter);
    }
  }

  private CategoryContainer<String, Exercise> getContainer(List<Exercise> exercises) {
    return new ExerciseMuscleContainer(exercises);
  }

  /*        End        */
  /*********************/
}
