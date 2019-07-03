package com.example.strongcore;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Serializable {

    ViewPager viewPager;
    ArrayList<Exercise> exercises;
    public static User user;
    public ExerciseDAO exerciseDAO;
    private TrainingDAO trainingDAO;
    private TrainingExercisesDAO trainingExercisesDAO;
    private TextToSpeech textToSpeech;
    private HashMap<Integer, ArrayList<String>> coordinates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

        setContentView(R.layout.activity_main);

        user = (User) getIntent().getSerializableExtra("User");

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new CardAdapter( this));

        exerciseDAO = new ExerciseDAO(getApplicationContext());
        trainingDAO = new TrainingDAO(getApplicationContext());
        trainingExercisesDAO = new TrainingExercisesDAO(getApplicationContext());

        final Button configButton = findViewById(R.id.config_button);
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent configIntent = new Intent(MainActivity.this, ConfigActivity.class);
                configIntent.putExtra("User", user);
                MainActivity.this.startActivity(configIntent);
//                MainActivity.this.finish();
            }
        });
    }

    public void setBodyCoordinates(HashMap<Integer, ArrayList<String>> coordinates) {
        this.coordinates = coordinates;
    }
}
