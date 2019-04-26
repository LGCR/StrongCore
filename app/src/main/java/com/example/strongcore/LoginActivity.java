package com.example.strongcore;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    List<User> users;
    RegisterActivity registerActivity;
    UserDAO userDAO;

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

        setContentView(R.layout.activity_login);

        final TextInputEditText email = findViewById(R.id.login_name);
        final TextInputEditText  password = findViewById(R.id.login_password);
        userDAO = new UserDAO(getApplicationContext());
        Button enter_button = findViewById(R.id.enter_button);
        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (users != null) {
                    for (User user : users) {
                        Log.e("MSG: ", user.getPassword());
                        if (user.getEmail().equals(email.getText().toString())
                                && user.getPassword().equals(password.getText().toString())) {
                            PersistenceDAO persistenceDAO = new PersistenceDAO(getApplicationContext());
                            Persistence persistence = new Persistence(email.getText().toString());
                            persistenceDAO.insert(persistence);
                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            mainIntent.putExtra("User", user);
                            LoginActivity.this.startActivity(mainIntent);
                            LoginActivity.this.finish();
                        }
                    }
                }
            }
        });

        Button register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(mainIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userDAO != null && userDAO.tableExists()) {
            users = userDAO.getList();
        }
    }
}
