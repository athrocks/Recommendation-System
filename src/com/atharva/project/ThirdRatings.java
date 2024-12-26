package com.atharva.project;

import com.atharva.project.filters.Filter;
import com.atharva.project.filters.TrueFilter;

import java.util.ArrayList;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;

    public ArrayList<Rater> getMyRaters() {
        return myRaters;
    }

    public ThirdRatings() {
        // default constructor
        this("src/com/atharva/project/data/ratings.csv"); // calls second Constructor with this parameter
//        this("src/com/atharva/project/data/ratings_short.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    // raters
    public int getRaterSize() {
        return myRaters.size();
    }

    // movie ID , minimalRaters
    public double getAverageByID(String id, int minimalRaters) {

        double[] ratingValue = {0.0};
        int[] raterCnt = {0};
        myRaters.forEach(rater -> {
            if (rater.getRating(id) != -1) {
                ratingValue[0] += rater.getRating(id);
                raterCnt[0]++;
            }
        });

        if (raterCnt[0] >= minimalRaters) {
            return ratingValue[0] / raterCnt[0];
        } else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRating(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); // id of all movies that satisfied TrueFilter
        ArrayList<Rating> averageRatings = new ArrayList<>();

        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
//        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); // id of all movies that satisfied TrueFilter
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<>();

        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }

        return averageRatings;
    }



    public static void main(String[] args) {
        ThirdRatings secondRatings = new ThirdRatings();
    }
}