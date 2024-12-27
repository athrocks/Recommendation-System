package com.atharva.project;

import com.atharva.project.filters.*;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings () {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");

        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        int minNumOfRatings = 35;
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRating(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with " +
                minNumOfRatings + " or more rating(s) :");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre () {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");

//        System.out.println("Read data for " + RaterDatabase.size() + " raters");
//        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        int yearVar = 1990; // variable
        String genreVar = "Drama"; // variable

        YearAfterFilter yearAfterFilter = new YearAfterFilter (yearVar);
        GenreFilter genreFilter = new GenreFilter (genreVar);


        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(yearAfterFilter);
        allFilter.addFilter(genreFilter);

        int minNumOfRatings = 8;

        ArrayList<Rating> movies = fourthRatings.getAverageRatingsByFilter(minNumOfRatings, allFilter);

        System.out.println("Number of movies found are:- " + movies.size());

        Collections.sort(movies);
        movies.forEach(mov -> {
            System.out.println(
                    MovieDatabase.getTitle(mov.getItem())+ " " + mov.getValue()
                            + "  " + MovieDatabase.getYear(mov.getItem())
                            + "   \n " + MovieDatabase.getGenres(mov.getItem())
            );
        });
    }

    public void printSimilarRatings () {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");

//        System.out.println("Read data for " + RaterDatabase.size() + " raters");
//        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;

        ArrayList<Rating> similarRatings = fr.getSimilarRatings(id, numSimilarRaters, minimalRaters);

        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) "
                + "recommended for the rater with ID " + id + " with " + minimalRaters
                + " or more rating(s). " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre () {
        FourthRatings fr = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");

//        System.out.println("Read data for " + RaterDatabase.size() + " raters");
//        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        String genreVar = "Mystery";
        int minRaters = 5;
        String id = "968";
        int numSimilarRaters = 20;

        Filter genreFilter = new GenreFilter(genreVar);

        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minRaters,genreFilter);

        System.out.println("Number of movies found are:- " + movies.size());

        Collections.sort(movies);
        movies.forEach(mov -> {
            System.out.println(MovieDatabase.getTitle(mov.getItem())+ " " + mov.getValue()
                    + "  " + MovieDatabase.getGenres(mov.getItem()));
        });
    }

    public void printSimilarRatingsByDirectors () {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");

        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        int minRaters = 1;
        String id = "198";
        int numSimilarRaters = 5;

        String[] directorArr = directors.split(",");

        ArrayList<Rating> allMovies = new ArrayList<>();

        for (String director : directorArr) {
            Filter directorsFilter = new DirectorsFilter(director.trim());
            ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minRaters, directorsFilter);
            allMovies.addAll(movies);
        }

        System.out.println("Number of movies found are: " + allMovies.size());

        if (!allMovies.isEmpty()) {
            Collections.sort(allMovies);
            allMovies.forEach(mov -> {
                System.out.println(MovieDatabase.getTitle(mov.getItem()) + " " + mov.getValue()
                        + " \n   " + MovieDatabase.getDirector(mov.getItem()));
            });
        } else {
            System.out.println("No movies found for the given directors.");
        }
    }


    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull.csv");

//        System.out.println("Read data for " + RaterDatabase.size() + " raters");
//        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        String genreVar = "Drama"; // variable
        GenreFilter genreFilter = new GenreFilter (genreVar);

        int minMinutes = 80;
        int maxMinutes = 160;
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);

        AllFilters af = new AllFilters();
        af.addFilter(genreFilter);
        af.addFilter(minutesFilter);

        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
                (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) "
                + "recommended for the rater with ID " + id + " and with " + minimalRaters
                + " or more rating(s), in \"" + genreVar + "\" genre, that is(are) between " + minMinutes
                + " and " + maxMinutes + " minutes in length. " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
                    + " Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");

//        System.out.println("Read data for " + RaterDatabase.size() + " raters");
//        System.out.println("Read data for " + MovieDatabase.size() + " movies");

        int yearVar = 1975;
        YearAfterFilter yearAfterFilter = new YearAfterFilter (yearVar);

        int minMinutes = 70;
        int maxMinutes = 200;
        MinutesFilter minutesFilter = new MinutesFilter (minMinutes, maxMinutes);

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(minutesFilter);

        String id = "314"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
                (id, numSimilarRaters, minimalRaters, allFilters);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) "
                + "recommended for the rater with ID " + id + " and with " + minimalRaters
                + " or more rating(s), that is(are) between " + minMinutes + " and " + maxMinutes
                + " minutes in length and released after year " + yearVar + ". " + numSimilarRaters
                + " closest raters were considered.");

        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
                    + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: "
                    + MovieDatabase.getMinutes(rating.getItem()));
        }
    }


    public static void main(String[] args) {
        MovieRunnerSimilarRatings mr = new MovieRunnerSimilarRatings();
//        mr.printAverageRatings();
//        System.out.println("----------------------------");
//        mr.printAverageRatingsByYearAfterAndGenre();
//        System.out.println("----------------------------");
//        mr.printSimilarRatings();
//        System.out.println("----------------------------");
//        mr.printSimilarRatingsByGenre();
//        System.out.println("----------------------------");
//        mr.printSimilarRatingsByDirectors();
//        System.out.println("----------------------------");
//        mr.printSimilarRatingsByGenreAndMinutes();
//        System.out.println("----------------------------");
//        mr.printSimilarRatingsByYearAfterAndMinutes();
//        System.out.println("----------------------------");
    }
}
