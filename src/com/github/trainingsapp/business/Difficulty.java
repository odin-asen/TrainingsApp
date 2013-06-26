package com.github.trainingsapp.business;

/**
 * Diese Klasse beschreibt die Schwierigkeit einer Uebung.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Difficulty {
  private String mName;

  Difficulty(String name) {
    mName = name;
  }

  public String getName() {
    return mName;
  }

  public String toString() {
    return mName;
  }
}
