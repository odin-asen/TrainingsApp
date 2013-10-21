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
 * Date: 22.09.13
 */
public class ExerciseArrayAdapter extends ArrayAdapter<Exercise> {

  private Context mContext;

  public ExerciseArrayAdapter(FragmentActivity activity, List<Exercise> exercises) {
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
    if (mContext.getString(R.string.amateur).equals(exercise.getDifficulty().getName())) {
      drawableID = R.drawable.ic_action_n_dark;
    } else {
      drawableID = R.drawable.ic_action_sort_muscle;
    }
    itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableID, 0);
  }
}
