package com.github.trainingsapp.business;

/**
 * Diese Klasse repraesentiert eine Muskelpartie.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Muscle {
  private String mName;
  /**
   * Beschreibt die Prioritaet, die eine Muskelpartie in der zeitlichen Reihenfolge
   * bei einem Ganzkoerpertraining einnimmt. */
  private int mExercisePriority;

  Muscle(String name, int priority) {
    mName = name;
    mExercisePriority = priority;
  }

  public String getName() {
    return mName;
  }

  /** Gibt einen Wert fuer die trainingslogische Prioritaet zurueck. */
  public int getPriority() {
    return mExercisePriority;
  }

  public String toString() {
    return mName+" "+mExercisePriority;
  }
}
