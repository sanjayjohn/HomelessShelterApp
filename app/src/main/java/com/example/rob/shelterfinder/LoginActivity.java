package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signAccount = findViewById(R.id.button);
        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        email   = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    public void attemptLogin() {
        final String correctEmail = "username@test.com";
        final String correctPassword = "password";

        String emailString = email.getText().toString().trim();
        String passString = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(emailString, passString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            finish();
                            goToMain(true);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (email.getText().toString().equals(correctEmail)
                && password.getText().toString().equals(correctPassword)) {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        }
    }

    public void goToMain(boolean success) {
        if (success) {
            Intent intent = new Intent(getApplicationContext(), AppActivity.class);
            startActivity(intent);
        }
    }

    public void cancelLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
