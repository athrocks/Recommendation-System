package com.atharva.project;

import java.util.ArrayList;

// testing methods of SecondRatings
public class MovieRunnerAverage {

    public void printAverageRatings(int minRaters){
        SecondRatings sr = new SecondRatings();
        ArrayList<Movie> myMovies = sr.getMyMovies();
//        ArrayList<Rater> myRaters = sr.getMyRaters();

        myMovies.forEach(movie -> {
            double val = sr.getAverageByID(movie.getID(), minRaters);
            if (val != 0){
                System.out.println(val + " " + movie.getTitle());
            }
        });
    }

    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        String title = "No Country for Old Men";
        String id = sr.getID(title);
        System.out.println("Average rating of given title: " + title + " is "+  sr.getAverageRating(id));
    }

    public static void main(String[] args) {
        MovieRunnerAverage mr = new MovieRunnerAverage();
//        mr.printAverageRatings(3);
        mr.getAverageRatingOneMovie();
//        mr.printMoviesWithMinRatings(12);

    }
}
