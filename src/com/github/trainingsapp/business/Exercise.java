package com.github.trainingsapp.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Exercise-Objekt beschreibt eine Uebung.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class Exercise {
  private String mName;
  /** Uebungsbeschreibung */
  private String mText;
  /** Dateipfad der Anatomiebilddatei */
  private String mAnatomyPath;
  /** XML Datei, die die Animation beschreibt */
  private String mExecAnimationFile;

  private Difficulty mDifficulty;
  private List<Equipment> mEquipmentList;
  /** Liste der primaer trainierten Muskeln. */
  private List<Muscle> mPrimaryMuscles;
  /** Liste der sekundaer trainierten Muskeln. */
  private List<Muscle> mSecondaryMuscles;

  /****************/
  /* Constructors */

  public Exercise(String name, String text, String anatomyPath, String execAnimationFile,
                  Difficulty difficulty, List<Equipment> equipments, List<Muscle> primaryMuscles,
                  List<Muscle> secondaryMuscles) {
    mName = name;
    mText = text;
    mAnatomyPath = anatomyPath;
    mExecAnimationFile = execAnimationFile;
    mDifficulty = difficulty;
    mEquipmentList = new ArrayList<Equipment>(equipments);
    mPrimaryMuscles = new ArrayList<Muscle>(primaryMuscles);
    mSecondaryMuscles = new ArrayList<Muscle>(secondaryMuscles);
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  private <T>ArrayList<T> getArrayList(T[] array) {
    final ArrayList<T> list = new ArrayList<T>(array.length);
    for (T t : array) {
      list.add(t);
    }
    return list;
  }
  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */
  /*       End       */
  /*******************/

  /*********************/
  /* Getter and Setter */


  public String getName() {
    return mName;
  }

  public String getText() {
    return mText;
  }

  public String getAnatomyPath() {
    return mAnatomyPath;
  }

  public String getExecAnimationFile() {
    return mExecAnimationFile;
  }

  public Difficulty getDifficulty() {
    return mDifficulty;
  }

  public List<Equipment> getEquipmentList() {
    return mEquipmentList;
  }

  public List<Muscle> getPrimaryMuscles() {
    return mPrimaryMuscles;
  }

  public List<Muscle> getSecondaryMuscles() {
    return mSecondaryMuscles;
  }

  /*        End        */
  /*********************/

  /*****************/
  /* Inner classes */
  /*      End      */
  /*****************/
}