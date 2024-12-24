package com.atharva.project;

import edu.duke.*;

import java.util.*;

import org.apache.commons.csv.*;


//  This method process every record from the CSV file whose name is filename,
//  a file of movie information,
//  and return an ArrayList of type Movie with all of the movie data from the file
public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

//        id,title,year,country,genre,director,minutes,poster

//        for(CSVRecord record : parser){
//            String title = record.get("id");
//            System.out.println(title);
//        }

        ArrayList<Movie> movieList = new ArrayList<>();

        parser.forEach(record -> {
            String id =  record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            String minutes = record.get("minutes");
            String poster = record.get("poster");

            Movie mv = new Movie(id,title,year,genre,director,country,poster,minutes);
            movieList.add(mv);
        });

        return movieList;
    }

    public ArrayList<Rater> loadRaters (String filename){

        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();


        // raterId, Rating(movieId,rating)
        Map<String,ArrayList<Rating>> mpp = new HashMap<>();

        parser.forEach(record -> {
            String rater_id =  record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            String time = record.get("time");

            Rating rting = new Rating(movie_id,rating);

            if (mpp.containsKey(rater_id)) {
                mpp.get(rater_id).add(rting);
            }
            else {
                ArrayList<Rating> newRatingList = new ArrayList<>();
                newRatingList.add(rting);
                mpp.put(rater_id, newRatingList);
            }
        });

//        System.out.println(mpp);
//        mpp.forEach((key, value) -> {
//            System.out.println("Rater ID: " + key + " Ratings: " + value);
//        });


        // Rater class ---> raterId, ArrayList<Rating>
        ArrayList<Rater> raterList = new ArrayList<>();
        mpp.forEach((key,value) -> {
            Rater newRater = new Rater(key);
            for (Rating r : value) {
                newRater.addRating(r.getItem(), r.getValue());
            }
            raterList.add(newRater);
        });

        return raterList;
    }

    public static void main(String[] args) {
        FirstRatings obj = new FirstRatings();
//        ArrayList<Movie> movieList = obj.loadMovies("src/com/atharva/project/data/ratedmoviesfull.csv");

        ArrayList<Movie> movieList = obj.loadMovies("src/com/atharva/project/data/ratedmovies_short.csv");
//        obj.testLoadMovies(movieList);

        ArrayList<Rater> raterList = obj.loadRaters("src/com/atharva/project/data/ratings_short.csv");
//        ArrayList<Rater> raterList = obj.loadRaters("src/com/atharva/project/data/ratings.csv");
//        obj.testLoadRaters(raterList);

        System.out.println(movieList);
        System.out.println("---------------");
        System.out.println(raterList);

    }
}
