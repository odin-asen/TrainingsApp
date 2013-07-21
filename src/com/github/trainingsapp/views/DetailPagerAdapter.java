package com.github.trainingsapp.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/* Da der Adapter eine Objektsammlung ist wird von FragmentStatePagerAdapter geerbt */
public class DetailPagerAdapter extends FragmentStatePagerAdapter {
  private List<Fragment> mFragments;

  public DetailPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
    super(fm);
    mFragments = fragments;
  }

  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }
}