package com.github.trainingsapp.views;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.github.R;

/**
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 30.10.13
 */
public class EquipmentFragment extends Fragment {
  private View mRootView;
  private ImageView mEquipmentImage;
  private int mImageID;
  private Bitmap mBitmap;

  public EquipmentFragment() {
    mRootView = null;
    mEquipmentImage = null;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    mRootView = inflater.inflate(R.layout.fragment_equipment_image, container, false);
    mEquipmentImage = (ImageView) mRootView.findViewById(R.id.equipment_view);

    return mRootView;
  }

  @Override
  public void onResume() {
    super.onResume();
    DisplayMetrics metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

    int width = metrics.widthPixels;
    int height = metrics.heightPixels;
    mEquipmentImage.setImageBitmap(decodeSampledBitmapFromResource(
        getResources(), mImageID, width, height));
    mEquipmentImage.invalidate();
  }

  @Override
  public void onDestroy() {
    if(mBitmap != null)
      mBitmap.recycle();
    super.onDestroy();
  }

  /*********************/
  /* Getter and Setter */

  public void setImage(int imageID) {
    mImageID = imageID;
    if(mEquipmentImage != null) {
      DisplayMetrics metrics = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

      int width = metrics.widthPixels;
      int height = metrics.heightPixels;
      mEquipmentImage.setImageBitmap(decodeSampledBitmapFromResource(
          getResources(), imageID, width, height));
    }
  }

  /*        End        */
  /*********************/

  public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                int reqWidth, int reqHeight) {
    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
  }

  public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

      final int halfHeight = height / 2;
      final int halfWidth = width / 2;

      // Calculate the largest inSampleSize value that is a power of 2 and keeps both
      // height and width larger than the requested height and width.
      while ((halfHeight / inSampleSize) > reqHeight
          && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
      }
    }

    return inSampleSize;
  }
}
