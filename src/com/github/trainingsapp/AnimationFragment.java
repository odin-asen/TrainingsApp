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
  private static final String KEY_ANIMATION = "animation";

  private View mRootView;
  private ImageView mImageView;
  private AnimationDrawable mAnimation;

  /****************/
  /* Constructors */

  public static AnimationFragment newInstance(int animationID) {
    AnimationFragment f = new AnimationFragment();
    Bundle bdl = new Bundle(1);
    bdl.putInt(KEY_ANIMATION, animationID);
    f.setArguments(bdl);
    return f;
  }

  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    
    /* Hauptview speichern */
    if(mRootView == null)
      mRootView = inflater.inflate(R.layout.fragment_animation, container, false);
    
    return mRootView;
  }

  @Override
  public void onStart() {
    super.onStart();

    if(mImageView == null)
      mImageView = (ImageView) mRootView.findViewById(R.id.animation_view);

    mImageView.setBackgroundResource(getArguments().getInt(KEY_ANIMATION));

    /* Animation starten */
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
