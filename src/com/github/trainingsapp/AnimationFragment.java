package com.github.trainingsapp;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.github.R;

/**
 * Diese Fragment startet eine Animation aus verschiedenen Bildern.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class AnimationFragment extends Fragment {
  View mRootView;
  ImageView mImageView;
  private AnimationDrawable mAnimation;
  Bundle mArgs;

  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    if(mRootView == null)
      mRootView = inflater.inflate(R.layout.fragment_animation, container, false);
    return mRootView;
  }

  @Override
  public void onStart() {
    super.onStart();

    if(mImageView == null)
      mImageView = (ImageView) mRootView.findViewById(R.id.animation_view);

    mImageView.setBackgroundResource(mArgs.getInt(DetailPagerAdapter.KEY_ANIMATION));

    mAnimation = (AnimationDrawable) mImageView.getBackground();

    if(mAnimation != null)
      mAnimation.start();
  }

  @Override
  public void onPause() {
    super.onPause();

    if(mAnimation != null)
      mAnimation.stop();
  }

  public void setArgs(Bundle args) {
    mArgs = args;
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
