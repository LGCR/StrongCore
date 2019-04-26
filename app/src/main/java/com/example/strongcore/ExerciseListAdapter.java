package com.example.strongcore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseListAdapter extends ArrayAdapter<ExerciseModel> {

    private ArrayList<ExerciseModel> dataSet;
    Context mContext;

    public ExerciseListAdapter(ArrayList<ExerciseModel> data, Context context) {
        super(context, R.layout.listitem, data);
        this.dataSet = data;
        this.mContext=context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtExercise;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ExerciseModel exerciseModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem, parent, false);
            viewHolder.txtExercise = convertView.findViewById(R.id.exerciseTxt);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtExercise.setText(exerciseModel.getExercise());
        // Return the completed view to render on screen
        return convertView;
    }
}
