package com.scubacabs.init;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scubacabs.R;

public class RegisterDriverActivity extends AppCompatActivity {
    EditText registerdriver_name,registerdriver_email,registerdriver_mobile,registerdriver_pin,registerdriver_vehiclename,registerdriver_vehiclenumber,registerdriver_password,registerdriver_confirmpassword;
    Button registerdriver_signup;
    TextView registerdriver_click;
    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        getSupportActionBar().hide();

        registerdriver_name = findViewById(R.id.registerdriver_name);
        registerdriver_email = findViewById(R.id.registerdriver_email);
        registerdriver_mobile = findViewById(R.id.registerdriver_mobile);
        registerdriver_pin = findViewById(R.id.registerdriver_pin);
        registerdriver_vehiclename = findViewById(R.id.registerdriver_vehiclename);
        registerdriver_vehiclenumber = findViewById(R.id.registerdriver_vehiclenumber);
        registerdriver_password = findViewById(R.id.registerdriver_password);
        registerdriver_confirmpassword = findViewById(R.id.registerdriver_confirmpassword);
        registerdriver_signup = findViewById(R.id.registerdriver_signup);
        registerdriver_click = findViewById(R.id.registerdriver_click);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerdriver_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterDriverActivity.this,LoginDriverActivity.class);
                startActivity(intent);

            }
        });

        registerdriver_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, mobile, pin, vehiclename, vehiclenumber, password, confirmpassword;
                name = registerdriver_name.getText().toString();
                email = registerdriver_email.getText().toString();
                mobile = registerdriver_mobile.getText().toString();
                pin = registerdriver_pin.getText().toString();
                vehiclename = registerdriver_vehiclename.getText().toString();
                vehiclenumber = registerdriver_vehiclenumber.getText().toString();
                password = registerdriver_password.getText().toString();
                confirmpassword = registerdriver_confirmpassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    registerdriver_name.setError("Enter Your Name");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    registerdriver_email.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    registerdriver_mobile.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(pin)) {
                    registerdriver_pin.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(vehiclename)) {
                    registerdriver_vehiclename.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(vehiclenumber)) {
                    registerdriver_vehiclenumber.setError("Enter Your Email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    registerdriver_password.setError("Enter Your Password");
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    registerdriver_confirmpassword.setError("Enter Your Password Again");
                    return;
                }

                if (!password.equals(confirmpassword)) {
                    registerdriver_password.setError("Password Mismatch");
                    registerdriver_confirmpassword.setError("Password Mismatch");
                    return;
                }
                if (password.length() < 8) {

                    registerdriver_password.setError("Please enter password of minimum 8 digits");
                    registerdriver_password.requestFocus();
                    return;


                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterDriverActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterDriverActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

    }
    private void updateUI(FirebaseUser user){
        if(user==null)
        {

        }
        else
        {
            Intent intent = new Intent(RegisterDriverActivity.this,LoginDriverActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}
