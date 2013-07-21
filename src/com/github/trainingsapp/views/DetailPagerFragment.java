package com.github.trainingsapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

import java.util.ArrayList;
import java.util.List;

public class DetailPagerFragment extends Fragment {
  private static final int PAGE_COUNT = 3;

  private DetailPagerAdapter mDetailPagerAdapter;
  private Exercise mExercise;

  public DetailPagerFragment() {
    mExercise = null;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View rootView = inflater.inflate(R.layout.detail, container, false);

    /* Initialisierung des ViewPager-Objektes */
    ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.detail_pager);
    viewPager.setOffscreenPageLimit(PAGE_COUNT);
    viewPager.setAdapter(mDetailPagerAdapter);

    return rootView;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /* Initialisierung der Fragmentliste und des PagerAdapter-Objekts. */
    List<Fragment> fragments = getFragments();
    mDetailPagerAdapter = new DetailPagerAdapter(getChildFragmentManager(),
        fragments);
  }

  private List<Fragment> getFragments(){
    if(mExercise == null)
      return new ArrayList<Fragment>(0);

    List<Fragment> fList = new ArrayList<Fragment>(3);

    fList.add(DetailImageFragment.newInstance(mExercise.getAnatomyRID()));
    fList.add(DescriptionFragment.newInstance(mExercise.getText()));
    fList.add(AnimationFragment.newInstance(mExercise.getAnimationRID()));

    return fList;
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
  }
}