package com.github.trainingsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

public class DetailPagerFragment extends Fragment implements ViewPager.OnPageChangeListener {
  private DetailPagerAdapter mDetailPagerAdapter;
  private ViewPager mViewPager;
  private Exercise mExercise;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    final View rootView = inflater.inflate(R.layout.detail, container, false);
    mViewPager = (ViewPager) rootView.findViewById(R.id.detail_pager);
    mDetailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager());

    return rootView;
  }

  @Override
  public void onStart() {
    super.onStart();
    mViewPager = (ViewPager) getActivity().findViewById(R.id.detail_pager);
    mViewPager.setOnPageChangeListener(this);
    mViewPager.setOffscreenPageLimit(3);
    refresh();
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
  }

  public void refresh() {
    if(mDetailPagerAdapter != null)
      mDetailPagerAdapter.setExercise(mExercise);
    /* Setzt die Detailansicht, wenn der Adapter und das View
     * nicht null sind.
     */
    if(mViewPager != null && mDetailPagerAdapter != null) {
      if(!mDetailPagerAdapter.equals(mViewPager.getAdapter())) {
        new SetAdapterTask().execute();
      }
    }
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    Log.wtf("onPageScrolled", position+" "+positionOffset+" "+positionOffsetPixels);
  }

  @Override
  public void onPageSelected(int position) {
//    View view = mDetailPagerAdapter.getItem(position).getView();
//    if(view != null)
//      view.invalidate();
    Log.wtf("onPageSelected", ""+position);
  }

  @Override
  public void onPageScrollStateChanged(int state) {
    Log.wtf("onPageScrollStateChanged", ""+state);
  }

  private class SetAdapterTask extends AsyncTask<Void,Void,Void> {
    protected Void doInBackground(Void... params) {return null;}

    @Override
    protected void onPostExecute(Void result) {
      mViewPager.setAdapter(mDetailPagerAdapter);
      mViewPager.invalidate();
    }
  }
}