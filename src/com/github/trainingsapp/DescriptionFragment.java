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
  public static final String KEY_DESCRIPTION = "description";

  private TextView mTextView;

  /****************/
  /* Constructors */

  public static DescriptionFragment newInstance(String text) {
    DescriptionFragment f = new DescriptionFragment();
    Bundle bdl = new Bundle(1);
    bdl.putString(KEY_DESCRIPTION, text);
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
    // The last two arguments ensure LayoutParams are inflated
    // properly.
    View rootView = inflater.inflate(R.layout.fragment_description, container, false);
    mTextView = ((TextView) rootView.findViewById(R.id.description_view));
    return rootView;
  }

  public void onStart() {
    super.onStart();
    mTextView.setText(getArguments().getString(KEY_DESCRIPTION));
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
