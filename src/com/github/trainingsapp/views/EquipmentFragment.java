package com.github.trainingsapp.views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
  private Drawable mImage;

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
    mEquipmentImage.setImageDrawable(mImage);
    mEquipmentImage.invalidate();
  }

  /*********************/
  /* Getter and Setter */

  public void setImage(Drawable image) {
    mImage = image;
    if(mEquipmentImage != null) {
      mEquipmentImage.setImageDrawable(mImage);
    }
  }

  /*        End        */
  /*********************/
}
