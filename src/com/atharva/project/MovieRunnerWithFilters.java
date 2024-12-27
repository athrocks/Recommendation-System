package com.atharva.project;

import com.atharva.project.filters.*;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    // True Filter Applied here
    public void printAverageRatings(int minRaters) {
        ThirdRatings tr = new ThirdRatings();

        System.out.println("Number of raters are:- " + tr.getRaterSize());
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        System.out.println("Number of movies in database are:- " + MovieDatabase.size()); // size() static method hence you call like this

        ArrayList<Rating> movies = tr.getAverageRating(minRaters);
        System.out.println("Movies with minimum " + minRaters + " raters are:- " + movies.size());
        Collections.sort(movies);
//        movies.forEach(System.out::println);
        movies.forEach(mov -> {
            System.out.println("Rating of "+ MovieDatabase.getTitle(mov.getItem())  + " is: " + mov.getValue());
        });
    }

    public void printAverageRatingsByYear (int minRaters,int yearVar) {
        ThirdRatings tr = new ThirdRatings();

        Filter yearAfterFilter = new YearAfterFilter(yearVar);

        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters,yearAfterFilter);

        System.out.println("Number of movies found are:- " + movies.size());

        Collections.sort(movies);
//        movies.forEach(System.out::println);
        movies.forEach(mov -> {
            System.out.println("Rating of "+ MovieDatabase.getTitle(mov.getItem())
                    + " is: " + mov.getValue()
                    + " year " + MovieDatabase.getYear(mov.getItem()));
        });
    }

    public void printAverageRatingsByGenre (int minRaters,String genreVar) {
        ThirdRatings tr = new ThirdRatings();

        Filter genreFilter = new GenreFilter(genreVar);

        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters,genreFilter);

        System.out.println("Number of movies found are:- " + movies.size());

        Collections.sort(movies);
        movies.forEach(mov -> {
            System.out.println(MovieDatabase.getTitle(mov.getItem())+ " " + mov.getValue()
                    + "  " + MovieDatabase.getGenres(mov.getItem()));
        });
    }

    public void printAverageRatingsByMinutes(int minRaters,int minMinutes, int maxMinutes) {
        ThirdRatings tr = new ThirdRatings();

        Filter minutesFilter = new MinutesFilter(minMinutes,maxMinutes);

        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters,minutesFilter);

        System.out.println("Number of movies found are:- " + movies.size());

        Collections.sort(movies);
        movies.forEach(mov -> {
            System.out.println(MovieDatabase.getTitle(mov.getItem())+ " " + mov.getValue()
                    + " Time:- " + MovieDatabase.getMinutes(mov.getItem()));
        });
    }

    public void printAverageRatingsByDirectors (int minRaters,String directors) {
        ThirdRatings tr = new ThirdRatings();

        String[] directorArr = directors.split(",");

        ArrayList<Rating> allMovies = new ArrayList<>();

        for (String director : directorArr) {
            Filter directorsFilter = new DirectorsFilter(director.trim());
            ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters, directorsFilter);
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

    public void printAverageRatingsByYearAfterAndGenre  (int minRaters,int yearVar ,String genreVar) {
        ThirdRatings tr = new ThirdRatings();

        AllFilters allFilter = new AllFilters();

        Filter yearAfterFilter = new YearAfterFilter(yearVar);
        Filter genreFilter = new GenreFilter(genreVar);

        allFilter.addFilter(yearAfterFilter);
        allFilter.addFilter(genreFilter);

        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters,allFilter);

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

    public void printAverageRatingsByDirectorsAndMinutes(int minRaters, String directors, int minMinutes, int maxMinutes) {
        ThirdRatings tr = new ThirdRatings();

        String[] directorArr = directors.split(",");

        ArrayList<Rating> allMovies = new ArrayList<>();

        for (String director : directorArr) {
            AllFilters allFilter = new AllFilters();

            Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
            allFilter.addFilter(minutesFilter);

            Filter directorsFilter = new DirectorsFilter(director.trim());
            allFilter.addFilter(directorsFilter);

            ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minRaters, allFilter);

            allMovies.addAll(movies);
        }

        System.out.println("Number of movies found are: " + allMovies.size());

        if (!allMovies.isEmpty()) {
            Collections.sort(allMovies);

            allMovies.forEach(mov -> {
                System.out.println(MovieDatabase.getTitle(mov.getItem()) + " " + mov.getValue()
                        + " \n   " + MovieDatabase.getDirector(mov.getItem())
                        + " \n   " + MovieDatabase.getMinutes(mov.getItem())
                );
            });
        } else {
            System.out.println("No movies found matching the criteria.");
        }
    }

    public static void main(String[] args) {

//        String workingDir = System.getProperty("user.dir");
//        System.out.println("Current Working Directory: " + workingDir);
        MovieRunnerWithFilters mvr = new MovieRunnerWithFilters();
//        mvr.printAverageRatings(1);
//        mvr.printAverageRatingsByYear(1,2000);
//        mvr.printAverageRatingsByGenre(1,"Crime");
//        mvr.printAverageRatingsByMinutes(1,110,170);
//        mvr.printAverageRatingsByDirectors(1,"Charles Chaplin,Michael Mann,Spike Jonze");
//        mvr.printAverageRatingsByYearAfterAndGenre(1,1980,"Romance");
//        mvr.printAverageRatingsByYearAfterAndGenre(1,2013,"Drama");
//        mvr.printAverageRatingsByDirectorsAndMinutes(1,"Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola",30,170);


//        mvr.printAverageRatings(35);
//        mvr.printAverageRatingsByYear(20,2000);
//        mvr.printAverageRatingsByGenre(20,"Comedy");
//        mvr.printAverageRatingsByMinutes (5,105,135);
        mvr.printAverageRatingsByDirectors  (4,"Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
//        mvr.printAverageRatingsByYearAfterAndGenre (8,1990,"Drama");
//        mvr.printAverageRatingsByDirectorsAndMinutes(3,"Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack",90,180);
    }
}
