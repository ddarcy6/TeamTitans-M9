package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    EditText usernameText;
    EditText passwordText;
    TextView warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        warningText = (TextView) findViewById(R.id.warningText);

        Button loginUser = (Button) findViewById(R.id.loginButton);
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                String pwd = passwordText.getText().toString();
                String usr = usernameText.getText().toString();

                if (UserBase.getInstance().containsUsername(usr) &&
                        UserBase.getInstance().getUser(usr).getPassword().equals(pwd)) {
                    warningText.setText("");
                    UserBase.getInstance().setCurrentUserName(usr);
                    startActivity(intent);
                } else {
                    passwordText.setText("");
                    warningText.setText("Incorrect Username or Password");

                }

            }
        });

        Button clickBack = (Button) findViewById(R.id.backToWelcomeButton);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
                startActivity(intent);
            }
        });
    }

}
