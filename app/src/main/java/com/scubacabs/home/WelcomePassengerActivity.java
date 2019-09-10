package com.scubacabs.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.scubacabs.R;
import com.scubacabs.init.LoginPassengerActivity;

public class WelcomePassengerActivity extends AppCompatActivity {
    Button welcomepassenger_signout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_passenger);
        getSupportActionBar().hide();
        welcomepassenger_signout = findViewById(R.id.welcomepassenger_signout);
        mAuth = FirebaseAuth.getInstance();
        welcomepassenger_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(WelcomePassengerActivity.this, LoginPassengerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
