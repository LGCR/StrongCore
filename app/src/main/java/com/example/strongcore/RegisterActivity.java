package com.example.strongcore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
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

        setContentView(R.layout.activity_register);

        final TextInputEditText name = findViewById(R.id.register_name);
        final TextInputEditText email = findViewById(R.id.register_email);
        final TextInputEditText password = findViewById(R.id.register_password);
        final TextInputEditText confirm_password = findViewById(R.id.register_confirm_password);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()
                    && !email.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()
                    && !confirm_password.getText().toString().isEmpty()){
                    if (password.getText().toString()
                            .equals(confirm_password.getText().toString())){
                        UserDAO userDAO = new UserDAO(getApplicationContext());
                        User user = new User(name.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString());
                        userDAO.insert(user);
                        RegisterActivity.this.finish();
                    }
                }
            }
        });

    }
}
