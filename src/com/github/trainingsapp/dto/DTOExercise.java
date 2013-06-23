package com.github.trainingsapp.dto;

/**
 * Bildet einen Tabelleneintrag in der Datenbank für eine Uebung ab.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class DTOExercise {
  public long id;
  public String name;
  public String text;
  public String anatomyPath;
  public String animationDir;
  /** 0 - Anfaenger <br/>
   * 1 - Fortgeschrittener */
  public int difficulty;
  public DTOEquipment[] equipment;
  public DTOMuscle[] primaryMuscles;
  public DTOMuscle[] secondaryMuscles;
}
