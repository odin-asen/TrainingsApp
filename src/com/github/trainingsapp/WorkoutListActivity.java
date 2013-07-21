package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

/**
 * Diese Klasse startet das Programm. Eine Liste der möglichen Trainingsuebungen
 * wird angezeigt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class WorkoutListActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
  private DetailPagerFragment mDetailFragment;
  private ExerciseListFragment mListFragment;

  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListFragment = new ExerciseListFragment();
    getSupportFragmentManager().beginTransaction().add(
        R.id.main_container, mListFragment).commit();
    mDetailFragment = new DetailPagerFragment();
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    mDetailFragment = new DetailPagerFragment();
    final FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.main_container, mDetailFragment);
    transaction.addToBackStack(null);
    transaction.commit();
    mDetailFragment.setExercise((Exercise) parent.getAdapter().getItem(position));
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
