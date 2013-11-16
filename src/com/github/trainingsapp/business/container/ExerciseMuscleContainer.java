package com.github.trainingsapp.business.container;

import android.support.v4.util.ArrayMap;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.business.Muscle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Exercise-Objekte werden in Kategorien geordnet, die nach der beanspruchten Muskelpartie geordnet sind.
 * Die Kategorien selbst sind trainingssinnvoll, d.h. in welcher Reihenfolge Muskelpartien trainiert werden
 * sollen, geordnet.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 08.11.13
 */
public class ExerciseMuscleContainer extends CategoryContainer<String,Exercise> {
  /**
   * Das uergebene Array wird nicht veraendert. Es wird in ein anderes Array kopiert und
   * dieses sortiert.
   *
   * @param elements         Darf nicht leer oder null sein.
   */
  public ExerciseMuscleContainer(List<Exercise> elements) {
    super(elements, new MuscleComparator());
  }

  @Override
  protected void setCategories(List<Exercise> elements) {
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

class MuscleComparator implements Comparator<Exercise> {
  private final int RESULT_EQUAL = 0;

  @Override
  public int compare(Exercise object, Exercise object1) {
      /* Vergleiche Primaermuskeln */
    final int compareResult = compareMuscles(object.getPrimaryMuscles(), object1.getPrimaryMuscles());
    if(compareResult == RESULT_EQUAL) {
        /* Vergleiche Sekundaermuskeln */
      return compareMuscles(object.getSecondaryMuscles(), object1.getSecondaryMuscles());
    } else {
      if(compareResult > RESULT_EQUAL)
        return 1;
      else return -1;
    }
  }

  private int compareMuscles(List<Muscle> thisList, List<Muscle> thatList) {
    for (Muscle thisMuscle : thisList) {
      for (Muscle thatMuscle : thatList) {
        final int compareResult = thisMuscle.getName().compareTo(thatMuscle.getName());
        if(compareResult > RESULT_EQUAL)
          return 1;
        else if(compareResult < RESULT_EQUAL)
          return -1;
      }
    }

    return RESULT_EQUAL;
  }
}
