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
        new Difficulty(dto.difficulty), fromEquipmentDTO(dto.equipment),
        fromMuscleDTO(dto.primaryMuscles), fromMuscleDTO(dto.secondaryMuscles));
  }

  public List<Muscle> fromMuscleDTO(String[] dtoArray) {
    final List<Muscle> modelList = new ArrayList<Muscle>(dtoArray.length);
    for (String value : dtoArray) {
      modelList.add(new Muscle(value));
    }
    return modelList;
  }

  public List<Equipment> fromEquipmentDTO(String[] dtoArray) {
    final List<Equipment> modelList = new ArrayList<Equipment>(dtoArray.length);
    for (String value : dtoArray) {
      modelList.add(new Equipment(value));
    }
    return modelList;
  }

  public DTOExercise toDTO(Exercise exercise) {
    final DTOExercise dto = new DTOExercise();

    dto.anatomyPath = exercise.getAnatomyPath();
    dto.animationFile = exercise.getExecAnimationFile();
    dto.difficulty = exercise.getDifficulty().getName();
    dto.equipment = toDTO(exercise.getEquipmentList());
    dto.name = exercise.getName();
    dto.primaryMuscles = toDTO(exercise.getPrimaryMuscles());
    dto.secondaryMuscles = toDTO(exercise.getSecondaryMuscles());
    dto.text = exercise.getText();

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
