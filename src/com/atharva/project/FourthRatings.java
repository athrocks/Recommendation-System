package com.atharva.project;

import com.atharva.project.filters.Filter;
import com.atharva.project.filters.TrueFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {

    public FourthRatings() {
        this("ratings.csv");
    }

    public FourthRatings(String filename) {
        RaterDatabase.initialize(filename);
    }

    public double getAverageByID(String id, int minimalRaters) {
        RaterDatabase.initialize("ratings.csv");
        double[] ratingValue = {0.0};
        int[] raterCnt = {0};
        RaterDatabase.getRaters().forEach(rater -> {
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<>();

        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<>();

        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }

        Collections.sort(averageRatings);
        return averageRatings;
    }

    private double dotProduct(Rater me, Rater r) {
        HashMap<String, Rating> myRatedMovieIDs = me.getMyRatings();
        double dotPrd = 0.0;

        for (String movieId : myRatedMovieIDs.keySet()) {
            if(r.hasRating(movieId)) {
                double myRating = me.getRating(movieId);
                double otherRating = r.getRating(movieId);

                dotPrd += (myRating - 5.0) * (otherRating - 5.0);
            }
        }

        return dotPrd;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        if (me == null) {
            System.out.println("Rater with ID " + id + " not found.");
            return new ArrayList<>();
        }

        ArrayList<Rating> similarityList = new ArrayList<>();
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(me.getID())) {
                double dotPrd = dotProduct(me, r);

                if (dotPrd >= 0) {
                    Rating rting = new Rating(r.getID(), dotPrd);
                    similarityList.add(rting);
                }
            }
        }

        similarityList.sort(Collections.reverseOrder());
        return similarityList;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minRaters) {
        ArrayList<Rating> similarityList = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        ArrayList<Rating> weightedRatingsList = new ArrayList<>();

        for (String movieId : movies) {
            int movId = Integer.parseInt(movieId);
            if(hasMinRaters(movieId,minRaters,numSimilarRaters,similarityList)) {
                double sum = 0.0;
                double num = 0.0;
                double avg;
                for(int i = 0; i<numSimilarRaters; i++) {
                    Rating rtingObj = similarityList.get(i);
                    String raterId = rtingObj.getItem();
                    double weight = rtingObj.getValue();

                    Rater rater = RaterDatabase.getRater(raterId);
                    HashMap<String, Rating> movieRated = rater.getMyRatings();

                    for (String movieId2 : movieRated.keySet()) {
                        int rMovId = Integer.parseInt(movieId2);
                        if(rMovId == movId) {
                            sum += similarityList.get(i).getValue() * rater.getRating(movieId2);
                            num += 1.0;
                        }
                    }
                }
                if (num != 0){
                    avg = (double) sum / num;
                    Rating rating = new Rating(movieId, avg);
                    weightedRatingsList.add(rating);
                }
            }
        }

        weightedRatingsList.sort(Collections.reverseOrder());
        return weightedRatingsList;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minRaters, Filter filterCriterion) {
        ArrayList<Rating> weightedRatingsList = new ArrayList<>();
        ArrayList<Rating> similarityList = getSimilarities(id);
        ArrayList<Rating> ratingsList = new ArrayList<>();

        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriterion);

        for (String movieId : filteredMovies) {
            int movId = Integer.parseInt(movieId);
            if(hasMinRaters(movieId,minRaters,numSimilarRaters,similarityList)) {
                double sum = 0.0;
                double num = 0.0;
                double avg;
                for(int i = 0; i<numSimilarRaters; i++) {
                    Rating rtingObj = similarityList.get(i);
                    String raterId = rtingObj.getItem();
                    double weight = rtingObj.getValue();

                    Rater rater = RaterDatabase.getRater(raterId);
                    HashMap<String, Rating> movieRated = rater.getMyRatings();

                    for (String movieId2 : movieRated.keySet()) {
                        int rMovId = Integer.parseInt(movieId2);
                        if(rMovId == movId) {
                            sum += similarityList.get(i).getValue() * rater.getRating(movieId2);
                            num += 1.0;
                        }
                    }
                }
                if (num != 0){
                    avg = (double) sum / num;
                    Rating rating = new Rating(movieId, avg);
                    weightedRatingsList.add(rating);
                }
            }
        }
        weightedRatingsList.sort(Collections.reverseOrder());
        return weightedRatingsList;
    }

    private boolean hasMinRaters(
            String movieId,
            Integer minimalRaters,
            Integer numSimilarRaters,
            ArrayList<Rating> similarityList) {
        int numOfRaters = 0;
        int movie_Id = Integer.parseInt(movieId);
        for (int i = 0; i < numSimilarRaters; i++) {
            Rater rater = RaterDatabase.getRater(similarityList.get(i).getItem());
            HashMap<String, Rating> movieRated = rater.getMyRatings();
            for (String movId : movieRated.keySet()) {
                int rMovId = Integer.parseInt(movId);
                if (rMovId == movie_Id) {
                    numOfRaters += 1;
                }
            }
        }
        return numOfRaters >= minimalRaters;
    }

    public static void main(String[] args) {
        FourthRatings fourthRatings = new FourthRatings();
    }
}
