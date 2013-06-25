package com.github.trainingsapp.business;

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
  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  public Exercise fromDTO(DTOExercise dto) {
    return new Exercise(dto.name, dto.text, dto.anatomyPath, dto.animationFile,
        Difficulty.valueOf(dto.difficulty), fromDTO(Equipment.class, dto.equipment),
        fromDTO(Muscle.class, dto.primaryMuscles), fromDTO(Muscle.class, dto.secondaryMuscles));
  }

  /** Wandelt ein Array mit Enumeration Objekten in eine Array List um. */
  public <T extends Enum>List<T> fromDTO(Class<T> clazz, String[] dtoArray) {
    final List<T> modelList = new ArrayList<T>(dtoArray.length);

    for (String value : dtoArray) {
      modelList.add(T.valueOf(clazz, value));
    }

    return modelList;
  }

  public DTOExercise toDTO(Exercise exercise) {
    final DTOExercise dto = new DTOExercise();

    dto.anatomyPath = exercise.getAnatomyPath();
    dto.animationFile = exercise.getExecAnimationFile();
    dto.difficulty = exercise.getDifficulty().name();
    dto.equipment = toDTO(exercise.getEquipmentList());
    dto.name = exercise.getName();
    dto.primaryMuscles = toDTO(exercise.getPrimaryMuscles());
    dto.secondaryMuscles = toDTO(exercise.getSecondaryMuscles());
    dto.text = exercise.getText();

    return dto;
  }

  public <T extends Enum>String[] toDTO(List<T> modelList) {
    final String[] dtoArray = new String[modelList.size()];

    for (int i = 0; i < modelList.size(); i++) {
      dtoArray[i] = modelList.get(i).name();
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
