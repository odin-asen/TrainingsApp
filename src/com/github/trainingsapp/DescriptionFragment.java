package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.R;

/**
 * Dieses Fragment zeigt die Details einer Trainingsuebung an.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class DescriptionFragment extends Fragment {
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
    View rootView = inflater.inflate(R.layout.fragment_description, container, false);
    Bundle args = getArguments();
    TextView textView = ((TextView) rootView.findViewById(R.id.description_view));
    textView.setText(args.getString(DetailPagerAdapter.KEY_DESCRIPTION));
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
