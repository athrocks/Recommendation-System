package com.atharva.project;

import java.util.*;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    public ArrayList<Rater> getMyRaters() {
        return myRaters;
    }

    public SecondRatings() {
        // default constructor
        this("src/com/atharva/project/data/ratedmoviesfull.csv", "src/com/atharva/project/data/ratings.csv"); // calls second Constructor
//        this("src/com/atharva/project/data/ratedmovies_short.csv", "src/com/atharva/project/data/ratings_short.csv"); // calls second Constructor
    }

    public SecondRatings(String moviefile , String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    // movie ID , minimalRaters
    public double getAverageByID(String id, int minimalRaters) {

        double[] ratingValue = {0.0};
        int[] raterCnt = {0};
        myRaters.forEach(rater -> {
            if (rater.getRating(id) != -1){
                ratingValue[0] += rater.getRating(id);
                raterCnt[0]++;
            }
        });

        if (raterCnt[0] >= minimalRaters) {
            return ratingValue[0]/raterCnt[0];
        } else {
            return 0.0;
        }
    }

    public double getAverageRating(String id) {
        double[] ratingValue = {0.0};
        int[] raterCnt = {0};
        myRaters.forEach(rater -> {
            if (rater.getRating(id) != -1){
                ratingValue[0] += rater.getRating(id);
                raterCnt[0]++;
            }
        });

        if (raterCnt[0] != 0.0) {
            return ratingValue[0]/raterCnt[0];
        } else {
            return 0.0;
        }
    }

    // movieId
    public String getTitle(String id) {

        String[] title = {""};
        myMovies.forEach(movie -> {
            if (movie.getID().equals(id)) {
                title[0] = movie.getTitle();
            }
        });

        if (true) {
            return title[0];
        } else {
            return "Id Not Found";
        }
    }

    public String getID(String title) {
        String[] id = {""};
        myMovies.forEach(movie -> {
            if (movie.getTitle().equals(title)) {
                id[0] = movie.getID();
            }
        });

        if (true) {
            return id[0];
        } else {
            return "NO SUCH TITLE.";
        }
    }

    public static void main(String[] args) {
        SecondRatings secondRatings = new SecondRatings();
//        int movies = secondRatings.getMovieSize();
//        int raters = secondRatings.getRaterSize();
//        System.out.println("Total number of movies are:- "+ movies);
//        System.out.println("Total number of raters are:- "+ raters);

//        secondRatings.myMovies.forEach(System.out::println);
//        secondRatings.myRaters.forEach(System.out::println);

//        String movieId = "0113277";
//        int minRaters = 2;
//        System.out.println("Average rating for Id= " + movieId + " with minimal raters required= " + minRaters + " is :- " + secondRatings.getAverageByID(movieId, minRaters));

//        String movieId2 = "0006414";
//        System.out.println("Title for id= " + movieId2 + " is " + secondRatings.getTitle(movieId2));

//        String title1 = "Heat";
//        System.out.println("Id for Title:- " + title1 + " is " + secondRatings.getID(title1));

//        secondRatings.myRaters.forEach();
    }
}