package com.github.trainingsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.github.trainingsapp.business.Exercise;

import java.util.List;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

  private Exercise mExercise;
  private List<Fragment> mFragments;

  public DetailPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
    super(fm);
    mExercise = null;
    mFragments = fragments;
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
  }

  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return "OBJECT " + (position + 1);
  }
}