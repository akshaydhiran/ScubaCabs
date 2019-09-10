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
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.scubacabs.R;
import com.scubacabs.home.WelcomeDriverActivity;

import java.util.List;

public class LoginDriverActivity extends AppCompatActivity {
    EditText logindriver_email,logindriver_password;
    Button logindriver_loginbutton;
    TextView logindriver_forgotpassword,logindriver_signup;

    private FirebaseAuth mAuth;
    private static final String TAG = "LoginDriver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);
        getSupportActionBar().hide();
        logindriver_email = findViewById(R.id.logindriver_email);
        logindriver_password = findViewById(R.id.logindriver_password);
        logindriver_loginbutton = findViewById(R.id.logindriver_loginbutton);
        logindriver_forgotpassword = findViewById(R.id.logindriver_forgotpassword);
        logindriver_signup = findViewById(R.id.logindriver_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        logindriver_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDriverActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logindriver_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDriverActivity.this,RegisterDriverActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logindriver_loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email = logindriver_email.getText().toString();
                password = logindriver_password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    logindriver_email.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    logindriver_password.setError("Enter Your Email");
                    return;
                }

                // SIMPLE AUTHENTICATION CODE FOR SIGN IN WITH EMAIL AND PASSWORD
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginDriverActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginDriverActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });

                // EMAIL VERIFICATION CODE
                mAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                if (task.isSuccessful()) {
                                    SignInMethodQueryResult result = task.getResult();
                                    List<String> signInMethods = result.getSignInMethods();
                                    if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                                        // User can sign in with email/password
                                    } else if (signInMethods.contains(EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD)) {
                                        // User can sign in with email/link
                                    }
                                } else {
                                    Log.e(TAG, "Error getting sign in methods for user", task.getException());
                                }
                            }
                        });



            }
        });
    }

    private void updateUI(FirebaseUser user){
        if(user==null){
        }
        else{
            Intent intent = new Intent(LoginDriverActivity.this, WelcomeDriverActivity.class);
            startActivity(intent);

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
