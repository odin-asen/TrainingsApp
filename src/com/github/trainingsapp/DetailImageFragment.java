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
  private static final String KEY_ANATOMY = "anatomy";

  private View mRootView;
  private ImageView mImageView;

  /****************/
  /* Constructors */

  public static DetailImageFragment newInstance(int anatomyID) {
    DetailImageFragment f = new DetailImageFragment();
    Bundle bdl = new Bundle(1);
    bdl.putInt(KEY_ANATOMY, anatomyID);
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
    if(mRootView == null)
      mRootView = inflater.inflate(R.layout.fragment_detail_image, container, false);
    return mRootView;
  }

  public void onResume() {
    super.onResume();
    if(mImageView == null)
      mImageView = ((ImageView) mRootView.findViewById(R.id.anatomy_view));
    mImageView.setBackgroundResource(getArguments().getInt(KEY_ANATOMY));
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
