package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import com.github.R;
import com.github.trainingsapp.business.Converter;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.data.DatabaseAccessor;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse startet das Programm. Eine Liste der möglichen Trainingsuebungen
 * wird angezeigt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class WorkoutListActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
  private DatabaseAccessor dbAccessor;
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

    dbAccessor = new DatabaseAccessor(this);
    dbAccessor.open();

    Converter converter = new Converter(this);
    final List<DTOExercise> dtos = dbAccessor.getAllExercises();
    dbAccessor.fillListObjects(dtos);
    final List<Exercise> values = new ArrayList<Exercise>(dtos.size());
    for (DTOExercise dto : dtos) {
      //TODO nullpointer bei converter abfangen
      values.add(converter.fromDTO(dto));
    }

    ((ExerciseListFragment) getSupportFragmentManager().findFragmentById(R.id.list))
        .setExercises(values);
    ((ListView) findViewById(R.id.list_view)).setOnItemClickListener(this);
//    mListFragment = new ExerciseListFragment(this, values);
//    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    ft.add(R.id.main_container, mListFragment);
//    ft.commit();
//    ((ListView) findViewById(R.id.list)).setOnItemClickListener(this);
//    mDetailFragment = new DetailPagerFragment(getSupportFragmentManager());
//    ft = getSupportFragmentManager().beginTransaction();
//    ft.add(R.id.main_container, mDetailFragment);
//    ft.commit();

  }

  @Override
  protected void onResume() {
    dbAccessor.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    dbAccessor.close();
    super.onPause();
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Adapter a = parent.getAdapter();
    //TODO mDetailFragment ist noch null
    mDetailFragment.setExercise((Exercise) a.getItem(position));
//        view.setVisibility(View.INVISIBLE);
//    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    ft.show(mDetailFragment);
//    ft.commit();
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
