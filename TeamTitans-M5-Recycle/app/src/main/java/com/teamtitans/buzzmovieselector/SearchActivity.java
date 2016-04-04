package com.teamtitans.buzzmovieselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.io.InputStreamReader;

import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;


public class SearchActivity extends AppCompatActivity {

    EditText movieTitle;
    String url;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        url = "http://www.omdbapi.com/?t=";

        final MovieResultsList movielist = MovieResultsList.getInstance(this.getApplicationContext());
        queue = Volley.newRequestQueue(this);
        movieTitle = (EditText) findViewById(R.id.movieTitle);

        Button searchMovie = (Button) findViewById(R.id.searchMovieButton);
        searchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                String title = movieTitle.getText().toString();
                title = title.replace("//s", "+");
                url = url + title + "&page=1";

                JsonArrayRequest jsObjRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray resp) {
                                movielist.clear();
                                movielist.addMovie(new Movie(url, 404));
                                JSONArray obj;
                                try {
                                    obj = resp;
                                    movielist.addMovie(new Movie("OBJ isn't null at least", 404));
                                } catch (NullPointerException e) {
                                    obj = new JSONArray();
                                    movielist.addMovie(new Movie("No response from server", 404));
                                }
                                if (obj.optString(obj.length() - 1).equals("True")) {
                                    JSONArray arr = obj.optJSONArray(0);
                                    movielist.addMovie(new Movie("Response was true", 404));
                                    try {
                                        for (int i = 0; i < arr.length(); i++) {
                                            try {
                                                JSONArray temp = arr.getJSONArray(i);
                                                String name = temp.optString(0);
                                                int year = temp.optInt(1);
                                                Movie temporary = new Movie(name, year);
                                                movielist.addMovie(temporary);
                                            } catch (JSONException e) {
                                                movielist.addMovie(new Movie("JSONException", 503));
                                                e.printStackTrace();
                                            }
                                        }
                                    } catch (NullPointerException e) {
                                        movielist.addMovie(new Movie("NullPointerException", 404));
                                        movielist.addMovie(new Movie("Movies not found", 503));
                                    }
                                } else {
                                    movielist.addMovie(new Movie("No Movies Found!", 404));

                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String disp = "JSon request failed";
                                System.out.println(disp);


                            }
                        });
                queue.add(jsObjRequest);
                startActivity(intent);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

}
