package com.github.trainingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

public class DetailPagerFragment extends Fragment {
  // When requested, this adapter returns a DemoObjectFragment,
  // representing an object in the collection.
  private DetailPagerAdapter mDetailPagerAdapter;

//  DetailPagerFragment(FragmentManager manager) {
//    mDetailPagerAdapter = new DetailPagerAdapter(manager);
//  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

//    final ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.detail_pager);
//    mDetailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager());
//    viewPager.setAdapter(mDetailPagerAdapter);

    return inflater.inflate(R.layout.detail, container);
  }

  public void setExercise(Exercise exercise) {
    mDetailPagerAdapter.setExercise(exercise);
  }
}