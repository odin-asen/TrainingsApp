package com.github.trainingsapp.business.container;

import android.support.v4.util.ArrayMap;
import com.github.trainingsapp.business.Exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Exercise-Objekte werden in Kategorien geordnet, die den gleichen
 * Anfangsbuchstaben im Namen haben.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 08.11.13
 */
public class ExerciseNameContainer extends CategoryContainer<String,Exercise> {
  /**
   * Das uergebene Array wird nicht veraendert. Es wird in ein anderes Array kopiert und
   * dieses sortiert.
   */
  public ExerciseNameContainer(List<Exercise> elements) {
    super(elements, new NameComparator());
  }

  @Override
  protected void setCategories(List<Exercise> elements) {
    /* Erstelle eine Liste mit den ersten Buchstaben des Alphabets,
     * die auch erster Buchstabe in den Uebungen sind. Jeder Buchstabe kommt
     * in der Liste nur einmal vor. */
    mCategories = new ArrayList<String>();
    String firstCharacter = elements.get(0).getName().substring(0,1);
    mCategories.add(firstCharacter);

    for (Exercise exercise : elements) {
      final String currentFirstCharacter = exercise.getName().substring(0,1);
      if(!firstCharacter.equals(currentFirstCharacter)) {
        firstCharacter = currentFirstCharacter;
        mCategories.add(firstCharacter);
      }
    }
  }

  @Override
  protected void setMapElements(List<Exercise> elements) {
    mAllElements = new ArrayMap<String, List<Exercise>>(mCategories.size());

    /* Gruppen initialisieren */
    for (String category : mCategories) {
      mAllElements.put(category, new ArrayList<Exercise>(2));
    }

    /* Listen fuellen */
    for (Exercise exercise : elements) {
      final String firstCharacter = exercise.getName().substring(0,1);
      mAllElements.get(firstCharacter).add(exercise);
    }
  }
}

class NameComparator implements Comparator<Exercise> {
  @Override
  public int compare(Exercise object, Exercise object1) {
    return object.getName().compareTo(object1.getName());
  }
}
