package com.github.trainingsapp.views;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.github.R;
import com.github.trainingsapp.business.Exercise;
import com.github.trainingsapp.business.container.CategoryContainer;

import java.util.List;
import java.util.Map;

/**
 * Diese Klasse verwaltet die Exercise-Objekte und dessen Darstellung.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 22.09.13
 */
public class ExerciseArrayAdapter extends BaseExpandableListAdapter {

  private Context mContext;
  private Map<String, List<Exercise>> mExercisesMap;
  private List<String> mGroupList;

  public ExerciseArrayAdapter(FragmentActivity activity, CategoryContainer<String,Exercise> container) {
    mContext = activity;
    mExercisesMap = container.getAllElements();
    mGroupList = container.getCategories();
  }

  public ExerciseArrayAdapter(FragmentActivity activity, List<String> groupList,
                              Map<String, List<Exercise>> exerciseMap) {
    mContext = activity;
    mGroupList = groupList;
    mExercisesMap = exerciseMap;
  }

  /*****************************/
  /* BaseExpandableListAdapter */

  @Override
  public int getGroupCount() {
    return mGroupList.size();
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return mExercisesMap.get(mGroupList.get(groupPosition)).size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return mGroupList.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return mExercisesMap.get(mGroupList.get(groupPosition)).get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    View row = convertView;

    if(row == null) {
      LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
      row = inflater.inflate(R.layout.exercise_group_item, parent, false);
    }

    ((TextView) row.findViewById(R.id.group_text)).setText(mGroupList.get(groupPosition));

    return row;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    View row = convertView;
    Exercise exercise = mExercisesMap.get(mGroupList.get(groupPosition)).get(childPosition);

    if(row == null) {
      LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
      row = inflater.inflate(R.layout.exercise_list_item, parent, false);
    }

    fillTextView((TextView) row.findViewById(R.id.item_text), exercise);

    return row;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }

  /*            End            */
  /*****************************/

  private void fillTextView(TextView itemTextView, Exercise exercise) {
    itemTextView.setText(exercise.getName());

    int drawableID;
    final String difficultyName = exercise.getDifficulty().getName();

    if (mContext.getString(R.string.leicht).equals(difficultyName)) {
      drawableID = R.drawable.ic_difficulty_easy;
    } else if(mContext.getString(R.string.mittel).equals(difficultyName)) {
      drawableID = R.drawable.ic_difficulty_medium;
    } else {
      drawableID = R.drawable.ic_difficulty_hard;
    }

    itemTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableID, 0);
  }
}
