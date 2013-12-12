package com.github.trainingsapp.views;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

  /**
   * Erstellt ein AnimationFragment Objekt. Der uebergebene Integerwert
   * wird in die ImageView bei onStart gelesen.
   */
  public static AnimationFragment newInstance(int animationID) {
    AnimationFragment fragment = new AnimationFragment();
    Bundle bdl = new Bundle(1);
    bdl.putInt(KEY_ANIMATION, animationID);
    fragment.setArguments(bdl);
    return fragment;
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

    mImageView.setImageResource(getArguments().getInt(KEY_ANIMATION));

    /* Animation starten */
    mAnimation = (AnimationDrawable) mImageView.getDrawable();

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
