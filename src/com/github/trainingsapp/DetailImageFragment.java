package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.github.R;

/**
 * Dieses Fragment zeigt ein Bild an, das als zusätzliche Detailinformation dient.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class DetailImageFragment extends Fragment {
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
    View rootView = inflater.inflate(R.layout.fragment_detail_image, container, false);
    Bundle args = getArguments();
    ImageView imageView = ((ImageView) rootView.findViewById(R.id.anatomy_view));

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
