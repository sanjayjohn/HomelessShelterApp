package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void attemptLogin(View view) {
        final String correctEmail = "username@test.com";
        final String correctPassword = "password";

        EditText email   = (EditText)findViewById(R.id.loginEmail);
        EditText password = (EditText)findViewById(R.id.loginPassword);

        if (email.getText().toString().equals(correctEmail)
                && password.getText().toString().equals(correctPassword)) {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        }
    }
}
