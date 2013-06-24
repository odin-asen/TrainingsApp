package com.github.trainingsapp.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.github.trainingsapp.dto.DTOExercise;

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

  /****************/
  /* Constructors */

  public DatabaseAccessor(Context context) {
    dbSchema = new DatabaseSchema(context);
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  public void open() throws SQLException {
    database = dbSchema.getWritableDatabase();
  }

  public void close() {
    dbSchema.close();
  }

  public void createExercises() {
    /* TODO füllen */
  }

  public void createExercise(DTOExercise exercise) {
    /* TODO füllen */
  }

  public void deleteExercise(DTOExercise exercise) {
    /* TODO füllen */
  }

  public List<DTOExercise> getAllExercises() {
    /* TODO füllen */
    return null;
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
