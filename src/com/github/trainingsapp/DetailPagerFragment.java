package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

public class DetailPagerFragment extends Fragment {
  private DetailPagerAdapter mDetailPagerAdapter;
  private ViewPager mViewPager;
  private Exercise mExercise;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    final View rootView = inflater.inflate(R.layout.detail, container, false);
    mViewPager = (ViewPager) rootView.findViewById(R.id.detail_pager);
    mDetailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager());
    refresh();

    return rootView;
  }

  @Override
  public void onStart() {
    super.onStart();
    mViewPager = (ViewPager) getActivity().findViewById(R.id.detail_pager);
    refresh();
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
    refresh();
  }

  public void refresh() {
    if(mDetailPagerAdapter != null)
      mDetailPagerAdapter.setExercise(mExercise);
    if(mViewPager != null && mDetailPagerAdapter != null) {
      if(!mDetailPagerAdapter.equals(mViewPager.getAdapter())) {
        mViewPager.setAdapter(mDetailPagerAdapter);
        mViewPager.invalidate();
      }
    }
  }
}