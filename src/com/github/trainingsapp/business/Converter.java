package com.github.trainingsapp.business;

import android.content.Context;
import android.content.res.Resources;
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
  private static final String TYPE_INTEGER = "integer";
  private static final String PKG_NAME = "com.github";
  private static final String PREFIX_DESCR = "description_";
  private static final String PREFIX_ANATO = "anatomy_";
  private static final String PREFIX_ANIMA = "animation_";
  private static final String DEFAULT_VALUE = "unknown";
  public static final int DEFAULT_VALUE_PRIORITY = 2147483647;
  private static final String NO_EQUIPMENT = "nichts";
  private static final int NO_RESOURCE = 0;

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
    int textRID = mRes.getIdentifier(PREFIX_DESCR + dto.name, TYPE_STRING, PKG_NAME);
    int anatomyRID = mRes.getIdentifier(PREFIX_ANATO + dto.name, TYPE_DRAWABLE, PKG_NAME);
    int difficultyRID = mRes.getIdentifier(dto.difficulty, TYPE_STRING, PKG_NAME);
    return new Exercise(getSafeResource(nameRID, DEFAULT_VALUE), getSafeResource(textRID, DEFAULT_VALUE),
        anatomyRID, getAnimationRIDs(PREFIX_ANIMA + dto.name), new Difficulty(getSafeResource(difficultyRID, DEFAULT_VALUE)),
        fromEquipmentDTO(dto.equipment), fromMuscleDTO(dto.primaryMuscles),
        fromMuscleDTO(dto.secondaryMuscles));
  }

  public List<Muscle> fromMuscleDTO(String[] dtoArray) {
    final List<Muscle> modelList = new ArrayList<Muscle>(dtoArray.length);
    for (String value : dtoArray) {
      int muscleRID = mRes.getIdentifier(value, TYPE_STRING, PKG_NAME);
      int exercisePriority = mRes.getIdentifier(value, TYPE_INTEGER, PKG_NAME);
      modelList.add(new Muscle(getSafeResource(muscleRID, DEFAULT_VALUE),
          getSafeResource(exercisePriority, DEFAULT_VALUE_PRIORITY)));
    }
    return modelList;
  }

  public List<Equipment> fromEquipmentDTO(String[] dtoArray) {
    final List<Equipment> modelList = new ArrayList<Equipment>(dtoArray.length);
    for (String value : dtoArray) {
      int equipmentRID = mRes.getIdentifier(value, TYPE_STRING, PKG_NAME);
      int equipmentImageID = mRes.getIdentifier(value, TYPE_DRAWABLE, PKG_NAME);
      modelList.add(new Equipment(getSafeResource(equipmentRID, DEFAULT_VALUE), equipmentImageID));
    }
    return modelList;
  }

  @SuppressWarnings("UnusedDeclaration")
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

  /**
   * Gibt einen String mit einer Nachricht ueber fehlende Resourcen zurueck.
   * Wenn keine Resource fehlt, wird ein leerer String zurueckgegeben.
   */
  public String checkResourcesForExercise(DTOExercise exercise) {
    final StringBuilder message = new StringBuilder(256);

    /* Pruefe Uebungsname,-beschreibung,-anatomiebild und -schwierigkeit */
    checkResource(message, exercise.name, TYPE_STRING, "exercise's name");
    checkResource(message, PREFIX_DESCR + exercise.name, TYPE_STRING, "exercise's description");
    checkResource(message, PREFIX_ANATO + exercise.name, TYPE_DRAWABLE, "exercise's image");
    checkResource(message, exercise.difficulty, TYPE_STRING, "exercise's difficulty");
    checkResource(message, PREFIX_ANIMA + exercise.name + "_1", TYPE_DRAWABLE, "exercise's animation");

    /* Pruefe Muskeln und Geraete */
    String[] array = exercise.primaryMuscles;
    for (String value : array) {
      checkResource(message, value, TYPE_STRING, "muscle's name for exercise "+exercise.name);
      checkResource(message, value, TYPE_INTEGER, "muscle's priority for exercise "+exercise.name);
    }

    array = exercise.equipment;
    for (String value : array) {
      if(!value.equals(NO_EQUIPMENT)) {
        checkResource(message, value, TYPE_STRING, "equipment's name for exercise " + exercise.name);
        checkResource(message, value, TYPE_DRAWABLE, "equipment's image for exercise "+exercise.name);
      }
    }

    return message.toString();
  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */

  private String getSafeResource(int resourceID, String defaultValue) {
    if(resourceID == NO_RESOURCE)
      return defaultValue;
    else return mRes.getString(resourceID);
  }

  private int getSafeResource(int resourceID, int defaultValue) {
    if(resourceID == NO_RESOURCE)
      return defaultValue;
    else return mRes.getInteger(resourceID);
  }

  private List<Integer> getAnimationRIDs(String name) {
    final List<Integer> idList = new ArrayList<Integer>(1);

    int resourceID = mRes.getIdentifier(name+ '_' +1, TYPE_DRAWABLE, PKG_NAME);
    for (int index = 2; resourceID != NO_RESOURCE; index++) {
      idList.add(resourceID);
      resourceID = mRes.getIdentifier(name+ '_' +index, TYPE_DRAWABLE, PKG_NAME);
    }

    return idList;
  }

  /**
   * Erstellt eine Nachricht, die wie folgt aussieht:<p/>
   * "Cannot find resource with name '{@code resourceName}' and type {@code resourceType}
   * for {@code forAttribute}."
   */
  private void checkResource(StringBuilder appendedMessage, String resourceName,
                             String resourceType, String forAttribute) {
    if(mRes.getIdentifier(resourceName, resourceType, PKG_NAME) == NO_RESOURCE) {
      appendedMessage.append("Cannot find resource with name \'")
          .append(resourceName)
          .append("\' and type ")
          .append(resourceType)
          .append(" for ")
          .append(forAttribute).append(".\n");
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
