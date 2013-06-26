package com.github.trainingsapp.business;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.github.R;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Mit dieser Klasse koennen DTO-Klassen in ihre business layer Pendants konvertiert werden
 * und umgekehrt.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Converter {
  private static final String TYPE_STRING = "string";
  private static final String TYPE_DRAWABLE = "drawable";
  private static final String PKG_NAME = "com.github";

  private Resources mRes;

  /****************/
  /* Constructors */

  public Converter(Context context) {
    mRes = context.getResources();
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  public Exercise fromDTO(DTOExercise dto) {
    int nameRID = mRes.getIdentifier(dto.name, TYPE_STRING, PKG_NAME);
    int textRID = mRes.getIdentifier(dto.name, TYPE_STRING, PKG_NAME);
    int anatomyRID = mRes.getIdentifier(dto.name, TYPE_DRAWABLE, PKG_NAME);
    int animationRID = mRes.getIdentifier(dto.name, TYPE_DRAWABLE, PKG_NAME);
    int difficultyRID = mRes.getIdentifier(dto.difficulty, TYPE_STRING, PKG_NAME);
    String string = mRes.getResourceTypeName(R.string.kniebeuge);
    string = mRes.getResourcePackageName(R.string.kniebeuge);
    string = mRes.getResourceEntryName(R.string.kniebeuge);
    string = mRes.getResourceName(R.string.kniebeuge);

    final String name = mRes.getString(nameRID);
    final String text = mRes.getString(textRID);
    final String schwierigkeit = mRes.getString(difficultyRID);
    return new Exercise(name, text,
        anatomyRID, animationRID, new Difficulty(schwierigkeit),
        fromEquipmentDTO(dto.equipment), fromMuscleDTO(dto.primaryMuscles),
        fromMuscleDTO(dto.secondaryMuscles));
  }

  public List<Muscle> fromMuscleDTO(String[] dtoArray) {
    final List<Muscle> modelList = new ArrayList<Muscle>(dtoArray.length);
    for (String value : dtoArray) {
      int muscleRID = mRes.getIdentifier(value, TYPE_STRING, PKG_NAME);
      modelList.add(new Muscle(mRes.getString(muscleRID)));
    }
    return modelList;
  }

  public List<Equipment> fromEquipmentDTO(String[] dtoArray) {
    final List<Equipment> modelList = new ArrayList<Equipment>(dtoArray.length);
    for (String value : dtoArray) {
      int equipmentRID = mRes.getIdentifier(value, TYPE_STRING, PKG_NAME);
      Log.wtf("wert",value);
      modelList.add(new Equipment(mRes.getString(equipmentRID)));
    }
    return modelList;
  }

  public DTOExercise toDTO(Exercise exercise) {
    final DTOExercise dto = new DTOExercise();

    dto.difficulty = exercise.getDifficulty().getName();
    dto.equipment = toDTO(exercise.getEquipmentList());
    dto.name = exercise.getName();
    dto.primaryMuscles = toDTO(exercise.getPrimaryMuscles());
    dto.secondaryMuscles = toDTO(exercise.getSecondaryMuscles());

    return dto;
  }

  public <T>String[] toDTO(List<T> modelList) {
    final String[] dtoArray = new String[modelList.size()];

    for (int i = 0; i < modelList.size(); i++) {
      dtoArray[i] = modelList.get(i).toString();
    }

    return dtoArray;
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
