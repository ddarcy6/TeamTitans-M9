package com.teamtitans.buzzmovieselector;

/**
 * Created by Alex on 3/7/2016
 */
public class Rating {

    private int totalRatings;
    private double avgRating;

    /**
     * adds a rating and averages it with the previous ratings inputted
     * @param input new rating to be added as a double type
     */
    public void addRating(double input) {
        totalRatings++;
        if (totalRatings == 1) {
            avgRating = input;
        } else {
            avgRating = ((((double) (totalRatings - 1)/((double) totalRatings)) * avgRating) + ((1/(double) totalRatings) * input));
        }
    }

    /**
     * returns the rating
     * @return a double that contains the rating
     */
    public double getAvgRating() {
        return avgRating;
    }

}
