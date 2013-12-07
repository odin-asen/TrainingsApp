package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.views.*;

/**
 * Diese Klasse startet das Programm. Eine Liste der möglichen Trainingsuebungen
 * wird angezeigt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class WorkoutListActivity extends FragmentActivity
    implements ExpandableListView.OnChildClickListener, TextView.OnClickListener, ListView.OnItemClickListener {
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

    mListFragment = new ExpandableListFragment();
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
    changeActionBar(item.getName(), true);
  }

  @Override
  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                              int childPosition, long id) {
    mDetailFragment = new DetailPagerFragment();
    final FragmentManager manager = getSupportFragmentManager();

    /* Liste mit Detail Ansicht ersetzen */
    /* addToBackStack erlaubt die Benutzung der Return-Taste */
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.main_container, mDetailFragment);
    transaction.addToBackStack(null);
    transaction.commit();

    /* Detailtext wird gesetzt */
    final Exercise item =
        (Exercise) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);

    mDetailFragment.setExercise(item);

    /* ActionBar Titel aendern, Knoepfe ausschalten */
    changeActionBar(item.getName(), true);

    return true;
  }

  @Override
  public void onClick(View v) {
    mEquipmentFragment = new EquipmentFragment();
    TextView clickedView = (TextView) v;
    Integer drawableID = (Integer) clickedView.getTag();

    final FragmentManager manager = getSupportFragmentManager();

    /* DetailFragment verstecken und das Unterlayout mit */
    /* EquipmentFragment Objekt besetzen. */
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.hide(mDetailFragment);
    transaction.replace(R.id.sub_container, mEquipmentFragment);
    transaction.addToBackStack(null);
    transaction.commit();

    mEquipmentFragment.setImage(getResources().getDrawable(drawableID));

    /* ActionBar Titel aendern, Knoepfe ausschalten */
    changeActionBar(clickedView.getText(), true);
  }

  @Override
  public void onBackPressed() {
    /* ActionBar Knoepfe anschalten */
    super.onBackPressed();

    changeActionBar(null, !mListFragment.isVisible());
    invalidateOptionsMenu();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.action_bar_items, menu);

    return !mInDetailView;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_sort_muscle:
        if(mListFragment.isVisible() &&
           !(mListFragment instanceof ExpandableListFragment)) {
          setListFragment(new ExpandableListFragment());
        }
        break;
      case R.id.action_sort_name:
        if(mListFragment.isVisible() &&
           !(mListFragment instanceof SimpleListFragment)) {
          setListFragment(new SimpleListFragment());
        }
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

  private void setListFragment(ExerciseListFragment fragment) {
    mListFragment = fragment;
    getSupportFragmentManager().beginTransaction().replace(
        R.id.main_container, mListFragment).commit();
  }
  /**
   *  Aendert den Titel der ActionBar, wenn der Parameter nicht null ist
   *  und setzt die Knoepfe je nach View.
   */
  private void changeActionBar(CharSequence clickedViewText, boolean inDetailView) {
    if(clickedViewText != null)
      getActionBar().setTitle(clickedViewText);
    mInDetailView = inDetailView;
    invalidateOptionsMenu();
  }

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
