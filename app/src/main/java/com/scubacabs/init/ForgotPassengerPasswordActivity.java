package com.scubacabs.init;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.scubacabs.R;

public class ForgotPassengerPasswordActivity extends AppCompatActivity {
    EditText loginpassenger_emailforgot;
    Button loginpassenger_buttonforgot;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passenger_password);
        loginpassenger_emailforgot = findViewById(R.id.loginpassenger_emailforgot);
        loginpassenger_buttonforgot = findViewById(R.id.loginpassenger_buttonforgot);
        getSupportActionBar().hide();

        loginpassenger_buttonforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email;
                email = loginpassenger_emailforgot.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    loginpassenger_emailforgot.setError("Please Enter Your Email");
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassengerPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgotPassengerPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            Log.d("TAG",""+task.getException());
                        }


                    }
                });


            }
        });
    }
}
