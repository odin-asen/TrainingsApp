package com.github.trainingsapp.business;

/**
 * Diese Klasse ist eine Liste von Muskelpartien, die waehrend einer Uebung angesprochen werden.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public enum Muscle {
  BICEPS("Bizeps"),
  CHEST("Brust"),
  QUADRICEPS("Quadrizeps"),
  TRICEPS("Trizeps");

  private String mName;

  Muscle(String name) {
    mName = name;
  }

  public String getName() {
    return mName;
  }
}
