package com.github.trainingsapp.data;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class DataAccessHelper {
  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  /** Load a file from the assets and return a byte array */
  public static byte[] loadAssetFile(AssetManager am, String filePath) {
    byte[] data = null;

    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      InputStream is = am.open(filePath);
      long time = System.currentTimeMillis();
      int oneByte = is.read();
      while (oneByte != -1) {
        bos.write(oneByte);
        oneByte = is.read();
      }
      Log.wtf("Lesedauer", String.valueOf(System.currentTimeMillis() - time));
      data = bos.toByteArray();
      is.close();
      bos.close();
    } catch (IOException ex) {
      /* TODO fehlermeldung weiterleiten */
    }

    return data;
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
