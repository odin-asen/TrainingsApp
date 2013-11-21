package com.github.trainingsapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.trainingsapp.business.Converter;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.data.DatabaseAccessor;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 20.11.13
 */
public abstract class ExerciseListFragment extends Fragment {
  protected DatabaseAccessor mDBAccessor;

  protected int mViewResource;

  /****************/
  /* Constructors */

  /** @param viewResource Gibt die View Datei an, die für dieses Fragment angezeigt wird. */
  public ExerciseListFragment(int viewResource) {
    mViewResource = viewResource;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(mViewResource, container, false);
  }

  @Override
  public void onResume() {
    refresh();
    super.onResume();
  }

  /**
   * Holt die Uebungen aus der Datenbank, unabhaengig von der Sortierungseinstellung.
   * openDatabase() muss vorher aufgerufen werden, wenn nicht schon getan.
   */
  private List<DTOExercise> getAllExercises() {
    List<DTOExercise> exercises = mDBAccessor.getAllExercises();
    mDBAccessor.fillListObjects(exercises);

    return exercises;
  }

  @Override
  public void onPause() {
    closeDatabase();
    super.onPause();
  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */

  /**
   * Oeffnet die Datenbank und laedt die Übungsliste (ruft setExercise).
   * super sollte beim Ueberschreiben aufgerufen werden.
   */
  protected void refresh() {
    final Activity activity = getActivity();

    /* Datenbank oeffnen und Exerciseliste holen. */
    openDatabase();
    Converter converter = new Converter(activity);
    final List<DTOExercise> dtos = getAllExercises();
    final List<Exercise> values = new ArrayList<Exercise>(dtos.size());
    for (DTOExercise dto : dtos) {
      values.add(converter.fromDTO(dto));
    }

    /* Exerciseliste und OnItemClickListener setzen */
    setExercises(values);
  }

  private void openDatabase() {
    if(mDBAccessor == null)
      mDBAccessor = new DatabaseAccessor(getActivity());
    mDBAccessor.open();
  }

  private void closeDatabase() {
    if(mDBAccessor != null)
      mDBAccessor.close();
  }

  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */

  /** Setzt Exercise-Objekte in die Liste. */
  abstract public void setExercises(List<Exercise> exercises);

  /*        End        */
  /*********************/
}
