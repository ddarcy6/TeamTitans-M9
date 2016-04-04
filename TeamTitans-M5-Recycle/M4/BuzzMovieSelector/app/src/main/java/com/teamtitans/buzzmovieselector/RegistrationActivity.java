package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {


    EditText usernameText;
    EditText passwordText;
    TextView warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        warningText = (TextView) findViewById(R.id.warningText);

        Button registerUser = (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                String pwd = passwordText.getText().toString();
                String usr = usernameText.getText().toString();

                if (UserBase.getInstance().containsUsername(usr)) {
                    warningText.setText("Username already registered");
                } else {
                    passwordText.setText("");
                    warningText.setText("Registration Successful!");
                    UserBase.getInstance().addUser(usr, pwd);
                    startActivity(intent);

                }

            }
        });

        Button clickBack = (Button) findViewById(R.id.backToWelcomeButton);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });
    }

}
