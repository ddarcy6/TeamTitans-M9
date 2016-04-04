package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultsActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Movie> movies = MovieResultsList.getInstance().getMovies();
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m : movies) {
            titles.add(m.getTitle());
        }

        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        ListView movielist = (ListView) findViewById(R.id.listView);
        movielist.setAdapter(adapter);



    }
}