package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        warningText = (TextView) findViewById(R.id.warningText);
        /**
         * Checks if user already exists in system. If not, registers user
         */

        Button registerUser = (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                String pwd = passwordText.getText().toString();
                String usr = usernameText.getText().toString();

                if (UserBase.getInstance().containsUsername(usr)) {
                    warningText.setText("Username is already in use!");
                    passwordText.setText("");
                } else {
                    UserBase.getInstance().addUser(usr, pwd);
                    startActivity(intent);
                }




            }
        });

        /**
         * Returns the user to the welcome screen without registering a new user
         */

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
