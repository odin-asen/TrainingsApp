package com.github.trainingsapp.business;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Ein Exercise-Objekt beschreibt eine Uebung.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Exercise {
  private long id;

  private String mName;
  private String mText;
  private Drawable mAnatomy;
  private AnimationDrawable mExecution;

  private Difficulty mDifficulty;
  private List<Equipment> mEquipmentList;
  /** List of primarily trained muscles. */
  private List<Muscle> mPrimaryMuscles;
  /** List of secondarily trained muscles. */
  private List<Muscle> mSecondaryMuscles;

  /****************/
  /* Constructors */

  public Exercise(String name, String text, Drawable anatomy, AnimationDrawable execution) {
    mName = name;
    mText = text;
    mAnatomy = anatomy;
    mExecution = execution;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */
  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */
  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */
  /*        End        */
  /*********************/

  /*****************/
  /* Inner classes */
  /*      End      */
  /*****************/
}