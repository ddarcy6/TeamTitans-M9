package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Used to control the suggestion button/function from the dasboard
 * @author Reed Rosser
 */
public class SuggestionActivity extends AppCompatActivity {
    TextView majorText;
    TextView titleText;
    TextView yearText;
    TextView warningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        majorText = (TextView) findViewById(R.id.majorTextView);
        titleText = (TextView) findViewById(R.id.movieTitleTextView);
        yearText = (TextView) findViewById(R.id.movieYearTextView);
        warningText = (TextView) findViewById(R.id.warningTextView);

        String currentUserString = UserBase.getInstance().getCurrentUserName();
        User currentUser = UserBase.getInstance().getUser(currentUserString);

        if(currentUser.getMajor() == null) {
            warningText.setText("Your profile is lacking a major");
            majorText.setText("");
            titleText.setText("");
            yearText.setText("");
        } else {
            warningText.setText("");
            Movie suggestion = MovieTopSuggestion.getInstance().getSuggestion(currentUser.getMajor());
            majorText.setText(currentUser.getMajor().toString());
            titleText.setText(suggestion.getTitle());
            yearText.setText(Integer.toString(suggestion.getYear()));
        }

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuggestionActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
