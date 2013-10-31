package com.github.trainingsapp.business;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
  private static final String PREFIX_DESCR = "description_";

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
    int textRID = mRes.getIdentifier(PREFIX_DESCR +dto.name, TYPE_STRING, PKG_NAME);
    int anatomyRID = mRes.getIdentifier(dto.name, TYPE_DRAWABLE, PKG_NAME);
    int animationRID = mRes.getIdentifier(dto.name, TYPE_DRAWABLE, PKG_NAME);
    int difficultyRID = mRes.getIdentifier(dto.difficulty, TYPE_STRING, PKG_NAME);
    return new Exercise(mRes.getString(nameRID), mRes.getString(textRID),
        anatomyRID, animationRID, new Difficulty(mRes.getString(difficultyRID)),
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
      int equipmentImageID = mRes.getIdentifier(value, TYPE_DRAWABLE, PKG_NAME);
      final Drawable drawable = getSafeDrawable(equipmentImageID);
      modelList.add(new Equipment(mRes.getString(equipmentRID), drawable));
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

  /** Gibt null zurueck, wenn die Ressource nicht existiert. */
  private Drawable getSafeDrawable(int drawableID) {
    try {
      return mRes.getDrawable(drawableID);
    } catch (Resources.NotFoundException ex) {
      Log.w(Converter.class.getName(), "Could not find drawable");
      return null;
    }
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
