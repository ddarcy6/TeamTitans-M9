package com.teamtitans.buzzmovieselector;

import java.util.HashMap;
import com.teamtitans.buzzmovieselector.User.MajorDegree;


/**
 * Created by reedrosser on 3/11/16.
 * Used in order to provide the top movie suggestion to the Suggestion Activity, which is used
 * in order to decide a suggestion for the user.
 */
public class MovieTopSuggestion {

    private static MovieTopSuggestion ourInstance = new MovieTopSuggestion();
    private HashMap<User.MajorDegree, Movie> topMovieSuggestion = new HashMap<>();
    public static MovieTopSuggestion getInstance() {return ourInstance;}

    /**
     * Decides the top movie suggestion for the user major
     */
    private MovieTopSuggestion() {

        Movie forCS = new Movie("Django Unchained", 2014);
        Movie forEE = new Movie("Frozen", 2013);
        Movie forCHEM = new Movie("Furtopia", 2016);
        Movie forISYE = new Movie("The Dark Knight", 2008);
        Movie forMATH = new Movie("The Avengers", 2009);
        Movie forPHYS = new Movie("Pulp Fiction", 1994);
        Movie forCHEME = new Movie("2001: A Space Odyssey", 1968);


        topMovieSuggestion.put(MajorDegree.CS, forCS);
        topMovieSuggestion.put(MajorDegree.EE, forEE);
        topMovieSuggestion.put(MajorDegree.CHEM, forCHEM);
        topMovieSuggestion.put(MajorDegree.ISYE, forISYE);
        topMovieSuggestion.put(MajorDegree.MATH, forMATH);
        topMovieSuggestion.put(MajorDegree.PHYS, forPHYS);
        topMovieSuggestion.put(MajorDegree.CHEME, forCHEME);
        //ME, ISYE, Math, Phys, ChemE

    }

    public Movie getSuggestion(MajorDegree major) {
        return topMovieSuggestion.get(major);
    }
}
