package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.github.R;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.views.DetailPagerFragment;
import com.github.trainingsapp.views.ExerciseListFragment;

/**
 * Diese Klasse startet das Programm. Eine Liste der möglichen Trainingsuebungen
 * wird angezeigt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class WorkoutListActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
  private DetailPagerFragment mDetailFragment;

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

    ExerciseListFragment listFragment = new ExerciseListFragment();
    getSupportFragmentManager().beginTransaction().add(
        R.id.main_container, listFragment).commit();
    mDetailFragment = new DetailPagerFragment();
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    mDetailFragment = new DetailPagerFragment();
    final FragmentManager manager = getSupportFragmentManager();

    /* Liste mit Detail Ansicht ersetzen */
    /* addToBackStack erlaubt die Benutzung der Return-Taste */
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.main_container, mDetailFragment);
    transaction.addToBackStack(null);
    transaction.commit();

    /* Detailtext wird gesetzt */
    final Exercise item = (Exercise) parent.getAdapter().getItem(position);

    mDetailFragment.setExercise(item);

    /* ActionBar Titel aendern */
    getActionBar().setTitle(item.getName());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.mainmenu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_sort_muscle:
        Toast.makeText(this, "Nach Muskeln sortieren", Toast.LENGTH_SHORT)
            .show();
        break;
      case R.id.action_sort_name:
        Toast.makeText(this, "Nach Name sortieren", Toast.LENGTH_SHORT)
            .show();
        break;

      default:
        break;
    }

    return true;
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
