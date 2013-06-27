package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.github.trainingsapp.business.Exercise;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DetailPagerAdapter extends FragmentStatePagerAdapter {
  private static final String KEY_DESCRIPTION = "description";
  private static final String KEY_ANATOMY = "anatomy";
  private static final String KEY_ANIMATION = "animation";
  private Exercise mExercise;

  public DetailPagerAdapter(FragmentManager fm) {
    super(fm);
    mExercise = null;
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
  }

  @Override
  public Fragment getItem(int i) {
    return createFragment(i);
  }

  public Fragment createFragment(int i) {
    Fragment fragment;
    final Bundle args = new Bundle();
    if(i == 0) {
      fragment = new DetailImageFragment();
      args.putInt(KEY_ANATOMY, mExercise.getAnatomyRID());
    } else if(i == 1) {
      fragment = new DescriptionFragment();
      args.putString(KEY_DESCRIPTION, mExercise.getText());
    } else {
      fragment = new AnimationFragment();
      args.putInt(KEY_ANIMATION, mExercise.getAnimationRID());
    }

    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getCount() {
    return 3;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return "OBJECT " + (position + 1);
  }
}