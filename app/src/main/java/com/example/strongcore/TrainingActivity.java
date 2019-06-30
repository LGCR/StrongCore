package com.example.strongcore;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class TrainingActivity extends AppCompatActivity {
    int totalExercises;
    int currentExercise = 0;
    ImageButton playPauseExercise;
    TextView timer;
    TextView sets;
    ImageView exerciseImg;
    TextView description;
    private Training training;
    private ArrayList<Exercise> exercises;
    private ArrayList<TrainingExercise> trainingExercises;
    private TextView time;
    private TextView title;
    public ImageButton mute_unmute;
    public boolean mute;
    private TrainingThread trainingThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        final View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        final int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {

            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    decorView.setSystemUiVisibility(uiOptions);
                }
            }
        });

        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        training = (Training) intent.getSerializableExtra("Training");
        exercises = (ArrayList<Exercise>) intent.getSerializableExtra("Exercises");
        trainingExercises = (ArrayList<TrainingExercise>) intent.getSerializableExtra("TrainingExercises");

        totalExercises = exercises.size();

        playPauseExercise = findViewById(R.id.training_play_pause);
        timer = findViewById(R.id.timer_text);
        time = findViewById(R.id.time_text);
        sets = findViewById(R.id.sets_text);
        exerciseImg = findViewById(R.id.exerciseImg);
        description = findViewById(R.id.exerciseDescription);
        title = findViewById(R.id.exerciseTitle);
        mute_unmute = findViewById(R.id.mute_unmute_button);

        setResources();

        trainingThread = new TrainingThread(this);
    }


    public void setResources(){
        exerciseImg.setImageResource(getResources()
                .getIdentifier(exercises.get(currentExercise)
                        .getExercise().toLowerCase().replaceAll("\\s+",""), "drawable", getPackageName()));
        title.setText(exercises.get(currentExercise).getExercise());
        sets.setText("0 de " + trainingExercises.get(currentExercise).getExerciseSets());
        time.setText( trainingExercises.get(currentExercise).getExerciseDuration() + "s");
        timer.setText("00:" + trainingExercises.get(currentExercise).getExerciseDuration());
    }

    public int getTotalExercises() {
        return totalExercises;
    }

    public int getCurrentExercise() {
        return currentExercise;
    }

    public ImageButton getPlayPauseExercise() {
        return playPauseExercise;
    }

    public TextView getTimer() {
        return timer;
    }

    public TextView getSets() {
        return sets;
    }

    public ImageView getExerciseImg() {
        return exerciseImg;
    }

    public TextView getDescription() {
        return description;
    }

    public Training getTraining() {
        return training;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public ArrayList<TrainingExercise> getTrainingExercises() {
        return trainingExercises;
    }

    public TextView getTime() {
        return time;
    }

    public TextView getTitlet() {
        return title;
    }

    public ImageButton getMute_unmute() {
        return mute_unmute;
    }

    public boolean isMute() {
        return mute;
    }
}
