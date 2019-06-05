package com.example.strongcore;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends PagerAdapter {

    int views[] = {R.layout.cardview, R.layout.cardview_body};
    LayoutInflater layoutInflater;
    Context context;
    List<Exercise> exercises;
    List<Training> trainings;
    List<TrainingExercise> trainingExercises;
    MainActivity mainActivity;
    Training t;

    CardAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.context = this.mainActivity;
    }

    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(views[position], container, false);
        if (position == 0) {
            exercises = new ExerciseDAO(context).getList();
            trainings = new TrainingDAO(context).getList();
            trainingExercises = new TrainingExercisesDAO(context).getList();
            final List<Exercise> tExercise = new ArrayList<>();
            for (Training training : trainings){
                if (training.getnTrainingsCompleted() < 3){
                    t = training;
                    Log.e("ENTROI", t.getIdTraining().toString());
                    break;
                }
            }
            for (TrainingExercise trainingExercise: trainingExercises){
                Log.e("ENTROI2", trainingExercise.getIdExercise().toString());
                if (t.getIdTraining().equals(trainingExercise.getIdTraining())){
                    tExercise.add(exercises.get(Integer.parseInt(trainingExercise.getIdExercise().toString())));
                }
            }
            ExerciseListAdapter adapter = new ExerciseListAdapter(tExercise, context);
            ListView listView = view.findViewById(R.id.list);
            listView.setAdapter(adapter);
            Button seeAllExercises = view.findViewById(R.id.main_see_all_exercises);
            seeAllExercises.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent allExercisesIntent = new Intent(v.getContext(), AllExercisesActivity.class);
                    mainActivity.startActivity(allExercisesIntent);
                }
            });
            Button startTraining = view.findViewById(R.id.startExercise);
            startTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent training = new Intent(v.getContext(), TrainingActivity.class);
                    training.putExtra("Training", t);
                    training.putExtra("Exercises", tExercise);
                    mainActivity.startActivity(training);
                }
            });
        }
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
