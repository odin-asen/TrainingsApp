package com.github.trainingsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Diese Klasse verwaltet das Datenbankschema. Das Schema wird verändert, wenn Tabellen gelöscht,
 * Spalten hinzugefügt oder gelöscht werden oder die Datenbank zum ersten mal erstellt wird.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 24.06.13
 */
public class DatabaseSchema extends SQLiteOpenHelper {

  /* Tabellenbezeichnungen */
  public static final String TABLE_EXERCISE = "exercise";
  public static final String TABLE_EQUIPMENT = "equipment";
  public static final String TABLE_MUSCLE = "muscle";
  public static final String TABLE_EX_EQ = "exerciseEquipment";
  public static final String TABLE_PRIMARY_MUSCLE = "primaryMuscle";
  public static final String TABLE_SECONDARY_MUSCLE = "secondaryMuscle";

  /* Allgemeine Spaltennamen */
  public static final String COLUMN_ID = "ID";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_ANIMATION_PATH = "animation";
  public static final String COLUMN_ANATOMY_PATH = "anatomy";
  public static final String COLUMN_DIFFICULTY = "difficulty";
  public static final String COLUMN_TEXT = "text";

  /* 1-zu-n, n-zu-m Spalten */
  public static final String COLUMN_ID_EXERCISE = "idExrcs";
  public static final String COLUMN_ID_EQUIPMENT = "idEqpmnt";
  public static final String COLUMN_ID_MUSCLE = "idMscl";

  private static final String DATABASE_NAME = "exercises.db";
  private static final int DATABASE_VERSION = 1;

  /* SQL Befehl fuer die Datenbankerstellung */
  private static final String CREATE_EXERCISE = "create table "
      + TABLE_EXERCISE + "("
      + COLUMN_ID + " integer primary key autoincrement, "
      + COLUMN_NAME + " text not null, "
      + COLUMN_TEXT + " text not null, "
      + COLUMN_ANIMATION_PATH + " text not null, "
      + COLUMN_ANATOMY_PATH + " text not null, "
      + COLUMN_DIFFICULTY + " text not null "
      +");";
  private static final String CREATE_EQUIPMENT = "create table "
      + TABLE_EQUIPMENT + "("
      + COLUMN_ID + " integer primary key autoincrement, "
      + COLUMN_NAME + " text not null, "
      +");";
  private static final String CREATE_MUSCLE = "create table "
      + TABLE_MUSCLE + "("
      + COLUMN_ID + " integer primary key autoincrement, "
      + COLUMN_NAME + " text not null, "
      +");";

  /* n-zu-m Tabellen */
  private static final String CREATE_EX_EQ = "create table "
      + TABLE_EX_EQ + "("
      + "foreign key(" + COLUMN_ID_EXERCISE + ") references " + TABLE_EXERCISE + "(" + COLUMN_ID + "),"
      + "foreign key(" + COLUMN_ID_EQUIPMENT + ") references " + TABLE_EQUIPMENT + "(" + COLUMN_ID + ")"
      +");";
  private static final String CREATE_PRIMARY_MUSCLE = "create table "
      + TABLE_PRIMARY_MUSCLE + "("
      + "foreign key(" + COLUMN_ID_EXERCISE + ") references " + TABLE_EXERCISE + "(" + COLUMN_ID + "),"
      + "foreign key(" + COLUMN_ID_MUSCLE + ") references " + TABLE_MUSCLE + "(" + COLUMN_ID + ")"
      +");";
  private static final String CREATE_SECONDARY_MUSCLE = "create table "
      + TABLE_SECONDARY_MUSCLE + "("
      + "foreign key(" + COLUMN_ID_EXERCISE + ") references " + TABLE_EXERCISE + "(" + COLUMN_ID + "),"
      + "foreign key(" + COLUMN_ID_MUSCLE + ") references " + TABLE_MUSCLE + "(" + COLUMN_ID + ")"
      +");";

  /****************/
  /* Constructors */

  public DatabaseSchema(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CREATE_EXERCISE);
    database.execSQL(CREATE_EQUIPMENT);
    database.execSQL(CREATE_MUSCLE);
    database.execSQL(CREATE_EX_EQ);
    database.execSQL(CREATE_PRIMARY_MUSCLE);
    database.execSQL(CREATE_SECONDARY_MUSCLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    Log.w(DatabaseSchema.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPMENT);
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSCLE);
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_EX_EQ);
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIMARY_MUSCLE);
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_SECONDARY_MUSCLE);
    onCreate(database);
  }

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
