package com.example.strongcore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConfigActivity extends AppCompatActivity {

    User user;

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

        setContentView(R.layout.activity_config);

        user = (User) getIntent().getSerializableExtra("User");

        TextView textView = findViewById(R.id.config_user_name);
        textView.setText(user.getName().split(" ")[0]);

        Button exit = findViewById(R.id.config_exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersistenceDAO persistenceDAO = new PersistenceDAO(getApplicationContext());
                List<Persistence> persistences = persistenceDAO.getList();
                Log.e("SIZE", String.valueOf(persistences.size()));
                for (Persistence user : persistences) {
                    Log.e("PER:", user.getEmail());
                    persistenceDAO.delete(user);
                }
                Intent intent = new Intent(ConfigActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ConfigActivity.this.startActivity(intent);
                ConfigActivity.this.finish();
            }
        });

        ImageButton backButton = findViewById(R.id.config_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigActivity.this.finish();
            }
        });
    }
}
