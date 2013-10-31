package com.github.trainingsapp.business;

import android.graphics.drawable.Drawable;

/**
 * Diese Klasse repraesentiert ein Geraet, dass für eine Uebung verwendet wird.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Equipment {
  private String mName;
  private Drawable mImage;

  Equipment(String name, Drawable image) {
    mName = name;
    mImage = image;
  }

  public String getName() {
    return mName;
  }

  public Drawable getImage() {
    return mImage;
  }

  public String toString() {
    return mName;
  }
}
