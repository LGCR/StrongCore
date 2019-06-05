package com.example.strongcore;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends BaseAdapter {

    private List<Training> trainings;
    private AllExercisesActivity allExercisesActivity;
    private ViewHolder viewHolder;

    CardListAdapter(List<Training> trainings, AllExercisesActivity allExercises){
        this.trainings = trainings;
        this.allExercisesActivity = allExercises;
        viewHolder = new ViewHolder();
    }

    static class ViewHolder{
        ArrayList<View> views = new ArrayList<>();
        TextView week;
        TextView trainings_available;
        TextView trainings_completed;
    }

    @Override
    public int getCount() {
        return trainings.size();
    }

    @Override
    public Object getItem(int position) {
        return trainings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if ((viewHolder.views.size()-1) < position) {
            viewHolder.views.add(position, allExercisesActivity.getLayoutInflater()
                    .inflate(R.layout.adapter_all_exercises, parent, false));
            viewHolder.week = viewHolder.views.get(position).findViewById(R.id.adapter_all_exercises_card_week_value);
            viewHolder.trainings_available = viewHolder.views.get(position).findViewById(R.id.adapter_all_exercises_card_nTainings_available_value);
            viewHolder.trainings_completed = viewHolder.views.get(position).findViewById(R.id.adapter_all_exercises_card_nTainings_completed_value);
            viewHolder.week.setText(Integer.toString(trainings.get(position).getWeek()));
            viewHolder.trainings_available.setText(Integer.toString(trainings.get(position).getnTrainingsAvailable()));
            viewHolder.trainings_completed.setText(Integer.toString(trainings.get(position).getnTrainingsCompleted()));
        }
        return viewHolder.views.get(position);
    }
}
