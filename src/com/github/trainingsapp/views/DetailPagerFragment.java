package com.github.trainingsapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Equipment;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.business.Muscle;

import java.util.ArrayList;
import java.util.List;

public class DetailPagerFragment extends Fragment {
  private static final int PAGE_COUNT = 3;

  private DetailPagerAdapter mDetailPagerAdapter;
  private Exercise mExercise;
  private View mRootView;

  public DetailPagerFragment() {
    mExercise = null;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    mRootView = inflater.inflate(R.layout.detail, container, false);

    /* Initialisierung des ViewPager-Objektes */
    ViewPager viewPager = (ViewPager) mRootView.findViewById(R.id.detail_pager);
    viewPager.setOffscreenPageLimit(PAGE_COUNT);
    viewPager.setAdapter(mDetailPagerAdapter);

    String NEW_LINE = "<p/>";
    String primaryMuscles = "Primaer: ";
    for (Muscle muscle : mExercise.getPrimaryMuscles()) {
      primaryMuscles = primaryMuscles + muscle.toString()+", ";
    }
    primaryMuscles = primaryMuscles + NEW_LINE;

    String secondaryMuscles = "Sekundaer: ";
    for (Muscle muscle : mExercise.getSecondaryMuscles()) {
      secondaryMuscles = secondaryMuscles + muscle.toString()+", ";
    }
    secondaryMuscles = secondaryMuscles + NEW_LINE;

    String equipments = "Geraet: ";
    for (Equipment equipment : mExercise.getEquipmentList()) {
      equipments = equipments + equipment.toString()+", ";
    }
    equipments = equipments + NEW_LINE;
    TextView textView = (TextView) mRootView.findViewById(R.id.detail_text);
    textView.setText(
        Html.fromHtml(mExercise.getName()+NEW_LINE+"Diff: "+mExercise.getDifficulty()+NEW_LINE+primaryMuscles+secondaryMuscles+equipments));

    return mRootView;
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