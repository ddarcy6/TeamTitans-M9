package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    EditText nameText;
    EditText year;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.nameText);
        nameText.setText(UserBase.getInstance().getCurrentUserName());
        year = (EditText) findViewById(R.id.yearText);

        /**
         *establish the spinner
         */

        dropdown = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<User.MajorDegree> adapter = new ArrayAdapter<User.MajorDegree>(this, android.R.layout.simple_spinner_dropdown_item, User.MajorDegree.values());
        dropdown.setAdapter(adapter);

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
                System.out.println("button clicked");
                UserBase theUserBase = UserBase.getInstance();
                System.out.println("userbase got");
                String currentName = theUserBase.getCurrentUserName();
                System.out.println("got user");
                theUserBase.editName(currentName, nameText.getText().toString());
                System.out.println("name edited");
                theUserBase.editMajor(currentName, (User.MajorDegree) dropdown.getSelectedItem());
                System.out.println("major edited");
                try {
                    theUserBase.editYear(currentName, Integer.parseInt(year.getText().toString()));
                } catch (java.lang.NumberFormatException e) {
                    // Set user's graduation year to 2018 by default
                    theUserBase.editYear(currentName, Integer.parseInt("2018"));
                }
                System.out.println("year changed");

//                User currentUser = UserBase.getInstance().getUser(currentName);
//                currentUser.setName(nameText.getText().toString());
//                currentUser.setMajor((User.MajorDegree)dropdown.getSelectedItem());
//                currentUser.setYear(Integer.parseInt(year.getText().toString()));
                Intent intent = new Intent(EditProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
                System.out.println("intent stuff");
            }
        });

    }
    //@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
