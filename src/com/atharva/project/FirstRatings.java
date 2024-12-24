package com.atharva.project;

import edu.duke.*;

import java.util.*;

import org.apache.commons.csv.*;


//  This method process every record from the CSV file whose name is filename,
//  a file of movie information,
//  and return an ArrayList of type Movie with all of the movie data from the file
public class FirstRatings {

    public void testLoadMovies(ArrayList<Movie> movieList){

//        System.out.println(movieList);
//        System.out.println("Total number of movies are:- " + movieList.size());
//        System.out.println("Movies are :");
//        movieList.forEach(System.out::println);

//        System.out.println("Comedy genre movies: ");
//        movieList.stream().filter(movie -> movie.getGenres().contains("Comedy")).forEach(System.out::println);
//        List<Movie> comedyList = movieList.stream().filter(movie -> movie.getGenres().contains("Comedy")).toList();
//        System.out.println(comedyList.size());
//
//        System.out.println("Movies duration grater than 150 minutes: ");
//        movieList.stream().filter(movie -> movie.getMinutes() >= 150).forEach(System.out::println);
//        List<Movie> moreThan150List = movieList.stream().filter(movie -> movie.getMinutes() > 150).toList();
//        System.out.println(moreThan150List.size());
//
//        System.out.println("Maximum number of movies by director: ");

//        Map<String,Integer> directorList = new HashMap<>();
//        for (Movie movie : movieList) {
//            if (directorList.containsKey(movie.getDirector())) {
//                directorList.put(movie.getDirector(), directorList.get(movie.getDirector()) + 1);
//            }
//            else {
//                directorList.put(movie.getDirector(), 1);
//            }
//        }
////        System.out.println(directorList);
//        // What is the maximum number of films directed by one director?
//        final int[] maxValue = {-1};
//        final String[] director = new String[1];
//        directorList.forEach((key,value)->{
//            if(value > maxValue[0]){
//                maxValue[0] = value;
//                director[0] = key;
//            }
////            System.out.println("key:- "+key + " & value:- "+value);
//        });
//        System.out.println(maxValue[0]);
//        System.out.println(director[0]);
    }

    public void testLoadRaters (ArrayList<Rater> raterList){
//        System.out.println(raterList);
//
//        System.out.println("Total number of raters = " + raterList.size());
//
//        System.out.println("-----------------------");
//        System.out.println("Each rater's Id and number of rating they did:");
//        raterList.stream().forEach(rater -> {
//            System.out.println("raterId = " + rater.getID() + " & moviesRated = " + rater.numRatings());
//            ArrayList<String> movieRatedList = rater.getItemsRated();
//            movieRatedList.forEach(movieId -> {
//                System.out.print("movieID = " + movieId);
//                System.out.print(" & rating value =  " + rater.getRating(movieId) + "\n");
//            });
//            System.out.println("--------------");
//        });

//        System.out.println("Number of rating for raterId = 2:- ");
//        Rater rater = raterList.get(1);
//        System.out.println("raterId = " + rater.getID());
//        System.out.println("number of ratings given by this rater:- " + rater.numRatings());
//
//        System.out.println("------------------------");

        // How many ratings does the rater number 193 have?
//        raterList.stream()
//                .filter(rater3 -> Integer.parseInt(rater3.getID()) == 193)
//                .forEach(rater3 -> {
//                    System.out.println("Rater ID: " + rater3.getID());
//                    System.out.println("Number of Ratings: " + rater3.numRatings());
//                });


//        What is the maximum number of ratings by any rater?
        // //        Which rater rated the most number of movies?
        // Rater{myID='99', myRatings=[[0454876, 8.0], [1649419, 8.0]]}]
//        int[] maxValue = {-1};
//        raterList.forEach(rater4 -> {
//            int val = rater4.numRatings();
//            if (val > maxValue[0]){
//                System.out.println(rater4.getID());
//                maxValue[0] = val;
//            }
//        });
//        System.out.println(maxValue[0]);





//        How many ratings does the movie “1798709” have?
//        <movieId,totalRaters>
//        Map<String,Integer> mpp2 = new HashMap<>();
//        raterList.forEach(rater2 -> {
//            ArrayList<String> movieRatedList2 = rater2.getItemsRated();
//            movieRatedList2.forEach(movieId -> {
//                if (mpp2.containsKey(movieId)) {
//                    mpp2.put(movieId, mpp2.get(movieId) + 1);
//                }
//                else {
//                    mpp2.put(movieId, 1);
//                }
//            });
//        });
//
//        mpp2.forEach((key,value) -> {
////            System.out.println("movieID :- " + key + " rated by " + value + " raters");
//            if (Integer.parseInt(key) == 1798709){
//                System.out.println(value);
//            }
//        });

//        What is the total number of unique movies that have been rated?
        // movieList total size
    }




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
