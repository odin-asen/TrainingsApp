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

import java.util.ArrayList;
import java.util.List;

public class DetailPagerFragment extends Fragment implements ViewPager.OnPageChangeListener {

  private View mRootView;
  private ViewPager mViewPager;
  private DetailPagerAdapter mDetailPagerAdapter;
  private Exercise mExercise;

  public DetailPagerFragment() {
    mExercise = null;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    mRootView = inflater.inflate(R.layout.detail, container, false);

    mViewPager = (ViewPager) mRootView.findViewById(R.id.detail_pager);
    mViewPager.setOnPageChangeListener(this);
    mViewPager.setOffscreenPageLimit(3);
    mViewPager.setAdapter(mDetailPagerAdapter);

    return mRootView;
  }

  public void setExercise(Exercise exercise) {
    mExercise = exercise;
//    mDetailPagerAdapter.setExercise(exercise);
//    setAdapter(mDetailPagerAdapter);
//    refresh();
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
//    Fragment fragment = mDetailPagerAdapter.getItem(position);
//    if(fragment != null) {
//      fragment.getActivity().findViewById(R.id.anatomy_view).invalidate();
//      fragment.getActivity().findViewById(R.id.animation_view).invalidate();
//      fragment.getActivity().findViewById(R.id.description_view).invalidate();
//    }
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