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
  private AnimationDrawable mAnimation;
  /****************/
  /* Constructors */
  /*     End      */
  /****************/

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    // The last two arguments ensure LayoutParams are inflated
    // properly.
    View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
    Bundle args = getArguments();
    ImageView imageView = (ImageView) rootView.findViewById(R.id.animation_view);
    imageView.setBackgroundResource(args.getInt(DetailPagerAdapter.KEY_ANIMATION));
    mAnimation = (AnimationDrawable) imageView.getBackground();
    return rootView;
  }

  @Override
  public void onStart() {
    super.onStart();

    mAnimation.start();
  }

  @Override
  public void onPause() {
    super.onPause();

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
