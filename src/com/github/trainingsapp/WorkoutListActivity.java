package com.github.trainingsapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
public class WorkoutListActivity extends ListActivity {
  private DatabaseAccessor dbAccessor;

  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    dbAccessor = new DatabaseAccessor(this);
    dbAccessor.open();

    Converter converter = new Converter();
    final List<DTOExercise> dtos = dbAccessor.getAllExercises();
    dbAccessor.fillListObjects(dtos);
    final List<Exercise> values = new ArrayList<Exercise>(dtos.size());
    for (DTOExercise dto : dtos) {
      //TODO nullpointer bei converter abfangen
      values.add(converter.fromDTO(dto));
    }

    // Use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
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
