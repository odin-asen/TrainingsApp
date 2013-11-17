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
   * @param elements Darf nicht leer oder null sein.
   */
  public ExerciseMuscleContainer(List<Exercise> elements) {
    super(elements, new ExerciseLogicComparator());
  }

  @Override
  protected void setCategories(List<Exercise> elements) {
    mCategories = new ArrayList<String>();
    String firstMuscle = elements.get(0).getPrimaryMuscles().get(0).getName();
    mCategories.add(firstMuscle);

    for (Exercise exercise : elements) {
      final String currentFirstMuscle = exercise.getPrimaryMuscles().get(0).getName();
      if(!firstMuscle.equals(currentFirstMuscle)) {
        firstMuscle = currentFirstMuscle;
        mCategories.add(firstMuscle);
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
      String firstMuscle = exercise.getPrimaryMuscles().get(0).getName();
      mAllElements.get(firstMuscle).add(exercise);
    }
  }
}

class ExerciseLogicComparator implements Comparator<Exercise> {
  @Override
  public int compare(Exercise object, Exercise object1) {
    return compareMuscles(object.getPrimaryMuscles(), object1.getPrimaryMuscles());
  }

  private int compareMuscles(List<Muscle> thisList, List<Muscle> thatList) {
    final int shorterLength;
    if(thisList.size() > thatList.size())
      shorterLength = thisList.size();
    else shorterLength = thatList.size();

    for (int i = 0; i < shorterLength; i++) {
      final int result = thisList.get(i).getPriority() - thatList.get(i).getPriority();
      if(result != 0)
        return result;
    }

    /* Die kuerzer Liste hat eine hoehere Prioritaet */
    return (thatList.size() - thisList.size());
  }
}
