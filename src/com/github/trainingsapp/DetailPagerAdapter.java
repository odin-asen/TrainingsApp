package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.github.trainingsapp.business.Exercise;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

  public static final String KEY_DESCRIPTION = "description";
  public static final String KEY_ANATOMY = "anatomy";
  public static final String KEY_ANIMATION = "animation";
  private Exercise mExercise;
  DetailImageFragment mAnatomyFragment;
  AnimationFragment mAnimationFragment;
  DescriptionFragment mDescriptionFragment;

  public DetailPagerAdapter(FragmentManager fm) {
    super(fm);
    mExercise = null;
    mAnatomyFragment = new DetailImageFragment();
    mAnimationFragment = new AnimationFragment();
    mDescriptionFragment = new DescriptionFragment();
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
      fragment = mAnatomyFragment;
      args.putInt(KEY_ANATOMY, mExercise.getAnatomyRID());
      mAnatomyFragment.setArgs(args);
    } else if(i == 1) {
      fragment = mDescriptionFragment;
      args.putString(KEY_DESCRIPTION, mExercise.getText());
      mDescriptionFragment.setArgs(args);
    } else {
      fragment = mAnimationFragment;
      args.putInt(KEY_ANIMATION, mExercise.getAnimationRID());
      mAnimationFragment.setArgs(args);
    }

    return fragment;
  }

  @Override
  public int getCount() {
    if(mExercise == null) {
      return 0;
    } else return 3;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return "OBJECT " + (position + 1);
  }
}