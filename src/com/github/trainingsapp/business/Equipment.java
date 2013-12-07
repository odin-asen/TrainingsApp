package com.github.trainingsapp.business;

/**
 * Diese Klasse repraesentiert ein Geraet, dass für eine Uebung verwendet wird.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Equipment {
  private String mName;
  private int mImage;

  Equipment(String name, int imageID) {
    mName = name;
    mImage = imageID;
  }

  public String getName() {
    return mName;
  }

  public int getImageID() {
    return mImage;
  }

  public String toString() {
    return mName;
  }
}
