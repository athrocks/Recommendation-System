package com.atharva.project;

import com.atharva.project.filters.Filter;
import com.atharva.project.filters.GenreFilter;

import java.util.ArrayList;
import java.util.Collections;

public class RecommendationRunner implements Recommender {

    private String genreVar;
    private int minRaters;
    private String id;
    private int numSimilarRaters;
    private Filter genreFilter;

    public RecommendationRunner() {
        this.genreVar = "Mystery";
        this.minRaters = 5;
        this.id = "968";
        this.numSimilarRaters = 20;
        this.genreFilter = new GenreFilter(genreVar);
    }

    @Override
    public ArrayList<String> getItemsToRate(){
        FourthRatings fr = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");

        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minRaters,genreFilter);

        ArrayList<String> movIdList = new ArrayList<>();
        movies.forEach(mov -> {
            movIdList.add(mov.getItem());
        });

        return movIdList;
    }

    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull.csv");
//        RaterDatabase.initialize("ratings");

        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</p>");

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(webRaterID, numSimilarRaters, minRaters,genreFilter);

        if (similarRatings.isEmpty()) {
            System.out.println("No matching movies were found");
        } else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) {
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>"
                        + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())
                        + "</td> </tr> ";
            }
            System.out.println(header  + body + "</table>");
        }
    }
}
