package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {

    EditText nameText;
    EditText major;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.nameText);
        major = (EditText) findViewById(R.id.majorText);
        year = (EditText) findViewById(R.id.yearText);

        /**
         * Returns the user to dashboard without editing values
         */

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
        /**
         * Sets the desired user data to the values specified by the user and returns them
         * to dashboard.
         */
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentName = UserBase.getInstance().getCurrentUserName();
                User currentUser = UserBase.getInstance().getUser(currentName);
                currentUser.setName(nameText.getText().toString());
                currentUser.setMajorStr(major.getText().toString());
                currentUser.setYear(Integer.parseInt(year.getText().toString()));
                Intent intent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

}
