package com.scubacabs.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.scubacabs.R;
import com.scubacabs.init.LoginDriverActivity;

public class WelcomeDriverActivity extends AppCompatActivity {
    Button welcomedriver_signout;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_driver);
        getSupportActionBar().hide();
        welcomedriver_signout = findViewById(R.id.welcomedriver_signout);
        mAuth = FirebaseAuth.getInstance();
        welcomedriver_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(WelcomeDriverActivity.this, LoginDriverActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
