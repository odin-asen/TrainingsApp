package com.github.trainingsapp;

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
    ImageView imageView = ((ImageView) rootView.findViewById(R.id.animation_view));
    //TODO animation setzen
    return rootView;
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
