package com.github.trainingsapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.github.trainingsapp.dto.DTOExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse greift auf die Datenbank zu. Mit einem Objekt dieser Klasse können
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 24.06.13
 */
public class DatabaseAccessor {

  private SQLiteDatabase database;
  private DatabaseSchema dbSchema;
  private Context mContext;

  /****************/
  /* Constructors */

  public DatabaseAccessor(Context context) {
    dbSchema = new DatabaseSchema(context);
    mContext = context;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  public void open() throws SQLException {
    database = dbSchema.getWritableDatabase();
//    myDataBase = SQLiteDatabase.openDatabase(getDatabasePath(), null, SQLiteDatabase.OPEN_READONLY);
  }

  public void close() {
    database.close();
    dbSchema.close();
  }

  /**
   * Fuellt eine Liste von Exercise-Objekten. Die Objekte sollten von {@link #getAllExercises()}
   * geholt werden, bevor sie in diese Methode uebergeben werden.
   * @param exercises Liste, die gefuellt wird.
   * @return Eine gefuellte Liste mit Exercise-Objekten.
   */
  public List<DTOExercise> fillListObjects(List<DTOExercise> exercises) {
    /* Abgefragte Tabellen (z.B.: from equipment, exerciseEquipment) */
    final String[] queryTables = new String[]{
        DatabaseSchema.TABLE_EQUIPMENT + "," + DatabaseSchema.TABLE_EX_EQ,
        DatabaseSchema.TABLE_MUSCLE + "," + DatabaseSchema.TABLE_PRIMARY_MUSCLE,
        DatabaseSchema.TABLE_MUSCLE + "," + DatabaseSchema.TABLE_SECONDARY_MUSCLE};

    for (DTOExercise exercise : exercises) {
      fillExerciseWithDatabaseContent(queryTables, exercise);
    }
    return exercises;
  }

  /**
   * Holt alle Uebungen aus der Datenbank und gibt diese in einer Liste zurueck.
   * Nur die Felder {@link DTOExercise#name} und {@link DTOExercise#difficulty}
   * werden gefuellt.
   */
  public List<DTOExercise> getAllExercises() {
    final List<DTOExercise> exercises = new ArrayList<DTOExercise>();

    final Cursor cursor = database.query(DatabaseSchema.TABLE_EXERCISE,
        new String[] {DatabaseSchema.COLUMN_NAME, DatabaseSchema.COLUMN_DIFFICULTY},
        null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      final DTOExercise exercise = cursorToExercise(cursor);
      exercise.anatomyPath = exercise.name+".jpeg";
      exercise.animationFile = exercise.name+".xml";
      exercise.text = exercise.name;
      exercises.add(exercise);
      cursor.moveToNext();
    }

    cursor.close();

    return exercises;
  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */

  private DTOExercise cursorToExercise(Cursor cursor) {
    final DTOExercise exercise = new DTOExercise();
    exercise.name = cursor.getString(DatabaseSchema.EXERCISE_INDEX_NAME);
    exercise.difficulty = cursor.getString(DatabaseSchema.EXERCISE_INDEX_DIFF);
    return exercise;
  }

  /** Interpretiert die erste Spalte als String und gibt ein Array zurueck.*/
  private String[] cursorToStringArray(Cursor cursor) {
    String[] strings = new String[cursor.getCount()];
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      strings[cursor.getPosition()] = cursor.getString(0);
      cursor.moveToNext();
    }
    return strings;
  }

  /** Fuellt die Variablen eines DTOExercise Objekts mit dem Datenbankinhalt */
  private void fillExerciseWithDatabaseContent(String[] queryTables, DTOExercise exercise) {
    final String[][] eqPrimSec = new String[queryTables.length][];
    for (int i = 0; i < queryTables.length; i++) {
      /* select name from queryTables[index]
       *               where exercise_id=NAME
       */
      final Cursor cursor = database.query(queryTables[i], new String[]{DatabaseSchema.COLUMN_NAME},
          DatabaseSchema.COLUMN_ID_EXERCISE+"="+exercise.name,null,null,null,null);
      eqPrimSec[i] = cursorToStringArray(cursor);
    }
    /* Uebertragen der Arrays */
    exercise.equipment = eqPrimSec[0];
    exercise.primaryMuscles = eqPrimSec[1];
    exercise.secondaryMuscles = eqPrimSec[2];
  }

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
