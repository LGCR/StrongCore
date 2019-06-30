package com.example.strongcore;

import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class TrainingThread extends Thread {

    private TrainingActivity trainingActivity;
    private ExerciseTimer exerciseTimer;
    private int currentSet = 0;

    TrainingThread(final TrainingActivity trainingActivity) {
        this.trainingActivity = trainingActivity;

        trainingActivity.getPlayPauseExercise().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTimer = new ExerciseTimer(TrainingThread.this,
                        ExerciseTimer.GET_READY_WARNING,/*
                        trainingActivity.getTrainingExercises()
                                .get(trainingActivity.getCurrentExercise())
                                .getExerciseDuration()**/1000, 1000);
            }
        });

        trainingActivity.getMute_unmute().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Speak(" ", trainingActivity);
            }
        });
    }

    @Override
    public void run() {
        super.run();
        while (true){

        }
    }

    public TrainingActivity getTrainingActivity() {
        return trainingActivity;
    }

    public void nextExercise(){
        trainingActivity.currentExercise++;
        trainingActivity.setResources();
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int getCurrentSet() {
        return currentSet;
    }
}
