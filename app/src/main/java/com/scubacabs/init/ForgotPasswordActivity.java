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

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText logindriver_emailforgot;
    Button logindriver_buttonforgot;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        logindriver_emailforgot = findViewById(R.id.logindriver_emailforgot);
        logindriver_buttonforgot = findViewById(R.id.logindriver_buttonforgot);
        getSupportActionBar().hide();

        logindriver_buttonforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email;
                email = logindriver_emailforgot.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    logindriver_emailforgot.setError("Please Enter Your Email");
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            Log.d("TAG",""+task.getException());
                        }


                    }
                });


            }
        });
    }
}
