package com.github.trainingsapp.business;

/**
 * Diese Klasse repraesentiert ein Geraet, dass für eine Uebung verwendet wird.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Equipment {
  private String mName;

  Equipment(String name) {
    mName = name;
  }

  public String getName() {
    return mName;
  }

  public String toString() {
    return mName;
  }
}
