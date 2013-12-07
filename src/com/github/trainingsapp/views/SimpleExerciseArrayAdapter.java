package com.github.trainingsapp.views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;

import java.util.List;

/**
 * Diese Klasse verwaltet die Exercise-Objekte und dessen Darstellung.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 20.11.13
 */
public class SimpleExerciseArrayAdapter extends ArrayAdapter<Exercise> {
  private Context mContext;

  public SimpleExerciseArrayAdapter(FragmentActivity activity, List<Exercise> exercises) {
    super(activity, android.R.layout.simple_list_item_1, exercises);
    mContext = activity;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View row = convertView;
    Exercise exercise = getItem(position);

    if(row == null) {
      LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
      row = inflater.inflate(R.layout.exercise_list_item, parent, false);
    }

    fillTextView((TextView) row.findViewById(R.id.item_text), exercise);

    return row;
  }

  private void fillTextView(TextView itemTextView, Exercise exercise) {
    itemTextView.setText(exercise.getName());
    int drawableID;
    if (mContext.getString(R.string.leicht).equals(exercise.getDifficulty().getName())) {
      drawableID = R.drawable.ic_difficulty_easy;
    } else {
      drawableID = R.drawable.ic_difficulty_hard;
    }
    itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableID, 0);
  }
}
