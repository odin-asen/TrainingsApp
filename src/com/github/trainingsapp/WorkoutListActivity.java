package com.github.trainingsapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.views.DetailPagerFragment;
import com.github.trainingsapp.views.EquipmentFragment;
import com.github.trainingsapp.views.ExerciseListFragment;

/**
 * Diese Klasse startet das Programm. Eine Liste der möglichen Trainingsuebungen
 * wird angezeigt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class WorkoutListActivity extends FragmentActivity implements AdapterView.OnItemClickListener, TextView.OnClickListener {
  private DetailPagerFragment mDetailFragment;
  private ExerciseListFragment mListFragment;
  private EquipmentFragment mEquipmentFragment;

  private boolean mInDetailView;

  /****************/
  /* Constructors */

  public WorkoutListActivity() {
    mInDetailView = false;
  }

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

    /* ActionBar Titel aendern, Knoepfe ausschalten */
    getActionBar().setTitle(item.getName());
    mInDetailView = true;
    invalidateOptionsMenu();
  }

  @Override
  public void onClick(View v) {
    mEquipmentFragment = new EquipmentFragment();
    TextView clickedView = (TextView) v;
    Drawable drawable = (Drawable) clickedView.getTag();

    final FragmentManager manager = getSupportFragmentManager();

    /* DetailFragment verstecken und das Unterlayout mit */
    /* EquipmentFragment Objekt besetzen. */
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.hide(mDetailFragment);
    transaction.replace(R.id.sub_container, mEquipmentFragment);
    transaction.addToBackStack(null);
    transaction.commit();

    mEquipmentFragment.setImage(drawable);

    /* ActionBar Titel aendern, Knoepfe ausschalten */
    getActionBar().setTitle(clickedView.getText());
  }

  @Override
  public void onBackPressed() {
    /* ActionBar Knoepfe anschalten */
    mInDetailView = false;
    invalidateOptionsMenu();
    super.onBackPressed();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    if(mInDetailView)
      inflater.inflate(R.menu.action_bar_items_detail, menu);
    else inflater.inflate(R.menu.action_bar_items, menu);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_sort_muscle:
        if(mListFragment.isVisible())
          mListFragment.setOrder(ExerciseListFragment.ORDER_BY_MUSCLE);
        break;
      case R.id.action_sort_name:
        if(mListFragment.isVisible())
          mListFragment.setOrder(ExerciseListFragment.ORDER_BY_NAME);
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
