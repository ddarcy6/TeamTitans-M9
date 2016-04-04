package com.teamtitans.buzzmovieselector;

import java.util.HashMap;

/**
 * Created by Alex on 2/24/2016
 */
public class Movie {

    private String title;
    private int year;
    private Rating overallRating = new Rating();

    public int getYear() {
        return year;
    }

    public void setYear(int input) {
        year = input;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String input) {
        title = input;
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String toString() {
        return title;
    }

    private HashMap<User.MajorDegree, Rating> ratings = new HashMap<>();

    /**
     * add a movie rating
     * @param major user major
     * @param score user movie rating score
     */
    public void addRating(User.MajorDegree major, int score) {
        if (ratings.containsKey(major)) {
            ratings.get(major).addRating((double) score);
        } else {
            ratings.put(major, new Rating());
            ratings.get(major).addRating((double) score);
        }
    }

    /**
     * movie's rating as decided by the collective users of that major
     * @param major user's major that is requesting rating
     * @return movie rating as decided by major's users
     */
    public double getRatingMajor(User.MajorDegree major) {
        if (ratings.containsKey(major)) {
            return ratings.get(major).getAvgRating();
        } else {
            return 0;
        }
    }

    /**
     * return average rating for all students and ratings for this movie
     * @return return average overall rating as a double
     */
    public double getOverallRating() {
        return overallRating.getAvgRating();
    }
}
