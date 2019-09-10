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


public class RegisterPassengerActivity extends AppCompatActivity {
    EditText registerpassenger_name,registerpassenger_email,registerpassenger_mobile,registerpassenger_password,registerpassenger_confirmpassword;
    Button registerpassenger_signup;
    TextView registerpassenger_click;

    private static final String TAG= "RegisterPassenger";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_passenger);
        getSupportActionBar().hide();
        registerpassenger_name = findViewById(R.id.registerpassenger_name);
        registerpassenger_email = findViewById(R.id.registerpassenger_email);
        registerpassenger_mobile = findViewById(R.id.registerpassenger_mobile);
        registerpassenger_password = findViewById(R.id.registerpassenger_password);
        registerpassenger_confirmpassword = findViewById(R.id.registerpassenger_confirmpassword);
        registerpassenger_signup = findViewById(R.id.registerpassenger_signup);
        registerpassenger_click = findViewById(R.id.registerpassenger_click);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerpassenger_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPassengerActivity.this,LoginPassengerActivity.class);
                startActivity(intent);

            }
        });
        registerpassenger_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email, mobile, password, confirmpassword;
                name = registerpassenger_name.getText().toString();
                email = registerpassenger_email.getText().toString();
                mobile = registerpassenger_mobile.getText().toString();
                password = registerpassenger_password.getText().toString();
                confirmpassword = registerpassenger_confirmpassword.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    registerpassenger_name.setError("Enter Your Name");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    registerpassenger_email.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    registerpassenger_mobile.setError("Enter Your Email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    registerpassenger_password.setError("Enter Your Password");
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    registerpassenger_confirmpassword.setError("Enter Your Password Again");
                    return;
                }

                if (!password.equals(confirmpassword)) {
                    registerpassenger_password.setError("Password Mismatch");
                    registerpassenger_confirmpassword.setError("Password Mismatch");
                    return;
                }
                if (password.length() < 8) {

                    registerpassenger_password.setError("Please enter password of minimum 8 digits");
                    registerpassenger_password.requestFocus();
                    return;

                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterPassengerActivity.this, new OnCompleteListener<AuthResult>() {
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
                                    Toast.makeText(RegisterPassengerActivity.this, "Authentication failed.",
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
            Intent intent = new Intent(RegisterPassengerActivity.this,LoginPassengerActivity.class);
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
