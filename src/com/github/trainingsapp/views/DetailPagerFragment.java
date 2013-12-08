package com.github.trainingsapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Equipment;
import com.github.trainingsapp.business.Exercise;

import java.util.ArrayList;
import java.util.List;

public class DetailPagerFragment extends Fragment {
  private static final int MAX_PAGE_COUNT = 3;

  private DetailPagerAdapter mDetailPagerAdapter;
  private Exercise mExercise;
  private View mRootView;
  private TextView.OnClickListener mOnClickListener;

  public DetailPagerFragment() {
    mExercise = null;
    mOnClickListener = null;
  }

  /***********/
  /* Methods */

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    mRootView = inflater.inflate(R.layout.detail, container, false);

    /* Initialisierung des ViewPager-Objektes */
    ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.detail_pager);
    viewPager.setOffscreenPageLimit(MAX_PAGE_COUNT);
    viewPager.setAdapter(mDetailPagerAdapter);

    setEquipmentTextViews();

    return mRootView;
  }

  @Override
  /** Activity muss TextView.OnClickListener implementieren. */
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (activity instanceof TextView.OnClickListener) {
      mOnClickListener = (TextView.OnClickListener) activity;
    } else {
      throw new ClassCastException(activity.toString()
          + " must implemenet TextView.OnClickListener");
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /* Initialisierung der Fragmentliste und des PagerAdapter-Objekts. */
    List<Fragment> fragments = getFragments();
    mDetailPagerAdapter = new DetailPagerAdapter(getChildFragmentManager(),
        fragments);
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    if(!hidden) {
      getActivity().getActionBar().setTitle(mExercise.getName());
    }

    super.onHiddenChanged(hidden);
  }

  /*   End   */
  /***********/

  /*********************/
  /* Getter and Setter */

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
  }

  /*        End        */
  /*********************/

  /*******************/
  /* Private Methods */

  private List<Fragment> getFragments(){
    if(mExercise == null)
      return new ArrayList<Fragment>(0);

    int animationCount = mExercise.getAnimationRID().size();
    List<Fragment> fList = new ArrayList<Fragment>(animationCount+2);

    fList.add(DetailImageFragment.newInstance(mExercise.getAnatomyRID()));
    fList.add(DescriptionFragment.newInstance(mExercise.getText()));

    for (int index = 0; index < animationCount; index++) {
      fList.add(AnimationFragment.newInstance(mExercise.getAnimationRID().get(index)));
    }

    return fList;
  }

  /** TextView Element zum horizontalen Layout hinzufuegen */
  private void setEquipmentTextViews() {
    final LinearLayout layout = (LinearLayout) mRootView.findViewById(R.id.equipment_text_layout);

    for (Equipment equipment : mExercise.getEquipmentList()) {
      final Button view = createEquipmentButton(equipment);
      layout.addView(view);
    }

    layout.invalidate();
  }

  private Button createEquipmentButton(Equipment equipment) {
    final String equipmentText = equipment.getName();
    final String noEquipment = getString(R.string.nichts);
    final Button button = new Button(getActivity());

    button.setText(equipmentText);
    button.setLayoutParams(new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    /* Button wird klickbar gemacht */
    if(!noEquipment.equals(equipmentText)) {
      button.setOnClickListener(mOnClickListener);
      button.setTag(equipment.getImageID());
    }

    return button;
  }

  /*       End       */
  /*******************/

  /*****************/
  /* Inner Classes */
  /*      End      */
  /*****************/
}