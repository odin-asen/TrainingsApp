package com.github.trainingsapp.business;

/**
 * Diese Klasse repraesentiert eine Muskelpartie.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Muscle {
  private String mName;

  Muscle(String name) {
    mName = name;
  }

  public String getName() {
    return mName;
  }

  public String toString() {
    return mName;
  }
}
