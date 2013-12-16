package com.github.trainingsapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.github.R;

/**
 * Dieses Fragment zeigt die Details einer Trainingsuebung an.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 23.06.13
 */
public class DescriptionFragment extends Fragment {
  public static final String KEY_DESCRIPTION = "description";
  private static final String UTF_8 = "utf-8";
  private static final String TEXT_HTML = "text/html";

  private WebView mTextView;

  /****************/
  /* Constructors */

  /**
   * Erstellt ein DescriptionFragment Objekt. Der uebergebene Stringwert
   * wird als Beschreibungstext verwendet.
   */
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
    mTextView = ((WebView) rootView.findViewById(R.id.description_view));
    return rootView;
  }

  public void onStart() {
    super.onStart();
    final String description = getArguments().getString(KEY_DESCRIPTION);
    final String content = "<html>" +
        "<head><style type=\"text/css\">body{color: #fff; background-color: #000;}></style></head>" +
        "<body>"+description+"</body></html>";
    mTextView.loadData(content, TEXT_HTML, UTF_8);
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
