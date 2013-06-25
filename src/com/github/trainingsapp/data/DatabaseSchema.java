package com.github.trainingsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Diese Klasse verwaltet das Datenbankschema. Das Schema wird verändert, wenn Tabellen gelöscht,
 * Spalten hinzugefügt oder gelöscht werden oder die Datenbank zum ersten mal erstellt wird.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 24.06.13
 */
public class DatabaseSchema extends SQLiteOpenHelper {


  /** Index fuer die Spalte name in der Tabelle exercise */
  public static final int EXERCISE_INDEX_NAME = 0;
  /** Index fuer die Spalte difficulty in der Tabelle exercise */
  public static final int EXERCISE_INDEX_DIFF = 1;

  /* Tabellenbezeichnungen */
  public static final String TABLE_EXERCISE = "exercise";
  public static final String TABLE_EQUIPMENT = "equipment";
  public static final String TABLE_MUSCLE = "muscle";
  public static final String TABLE_EX_EQ = "exerciseEquipment";
  public static final String TABLE_PRIMARY_MUSCLE = "primaryMuscle";
  public static final String TABLE_SECONDARY_MUSCLE = "secondaryMuscle";

  /* Allgemeine Spaltennamen */
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_DIFFICULTY = "difficulty";

  /* 1-zu-n, n-zu-m Spalten */
  public static final String COLUMN_ID_EXERCISE = "exercise_id";
  public static final String COLUMN_ID_EQUIPMENT = "equipment_id";
  public static final String COLUMN_ID_MUSCLE = "muscle_id";

  /* The Android's default system path of your application database. */
  private static String DATABASE_PATH = "/data/data/trainingsapp/databases/";
  private static final String DATABASE_NAME = "test.db";
  private static final int DATABASE_VERSION = 1;

  private Context mContext;

  /****************/
  /* Constructors */

  public DatabaseSchema(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    mContext = context;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  /**
   * Erstellt eine leere Datenbank im System und ueberschreibt diese mit der
   * vorbereiteten.
   */
  public void onCreate(SQLiteDatabase database) {
    if(!checkDataBase()){
      //By calling this method and empty database will be created into the default system path
      //of your application so we are gonna be able to overwrite that database with our database.
      this.getReadableDatabase();
      try {
        copyDataBase();
      } catch (IOException e) {
        throw new Error("Error copying database");
      }
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

  }

  /**
   * Copies your database from your local assets-folder to the just created empty database in the
   * system folder, from where it can be accessed and handled.
   * This is done by transfering bytestream.
   * */
  private void copyDataBase() throws IOException{
    InputStream in = mContext.getAssets().open(DATABASE_NAME);
    OutputStream out = new FileOutputStream(getDatabasePath());
    /* transfer bytes from the inputfile to the outputfile */
    byte[] buffer = new byte[1024];
    int length;
    while ((length = in.read(buffer))>0){
      out.write(buffer, 0, length);
    }

    /* Close the streams */
    out.flush();
    out.close();
    in.close();

  }

  public String getDatabasePath() {
    return DATABASE_PATH + DATABASE_NAME;
  }

  /*   End   */
  /***********/

  /*******************/
  /* Private Methods */

  /**
   * Check if the database already exist to avoid re-copying the file each time you open the application.
   * @return true if it exists, false if it doesn't
   */
  private boolean checkDataBase(){
    SQLiteDatabase checkDB = null;
    try{
      checkDB = SQLiteDatabase.openDatabase(getDatabasePath(), null, SQLiteDatabase.OPEN_READONLY);
    } catch(SQLiteException e) {
      /* Datenbank existiert noch nicht */
    }

    if(checkDB != null){
      checkDB.close();
      return true;
    } else return false;
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
