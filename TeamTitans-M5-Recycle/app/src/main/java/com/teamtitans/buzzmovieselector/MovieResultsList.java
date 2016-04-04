package com.teamtitans.buzzmovieselector;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex on 2/24/2016.
 */
public class MovieResultsList {
    private static MovieResultsList ourInstance = new MovieResultsList();

    public static MovieResultsList getInstance() {
        return ourInstance;
    }

    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private ArrayList<Movie> movies = new ArrayList<>();

    private MovieResultsList() {
    }

    public void setContext(Context input) {
        mCtx = input;
    }

    private MovieResultsList(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized MovieResultsList getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MovieResultsList(context);
        }
        return ourInstance;
    }

    public void addMovie(Movie input) {
        movies.add(input);
    }

    public void clear() {
        movies.clear();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }




}
