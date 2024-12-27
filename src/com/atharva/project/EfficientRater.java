package com.atharva.project;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID; // (raterId)
    // <movieId, Rating>
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public HashMap<String,Rating> getMyRatings() {
        return myRatings;
    }

//    (movieId,ratingValue)
    public void addRating(String item, double rating) {
        Rating newRating = new Rating(item, rating);
        myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    // returns raterID
    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(hasRating(item)) {
            return myRatings.get(item).getValue();
        }

        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }


    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }

        return list;
    }

    @Override
    public String toString() {
        return "Rater{" +
                "myID='" + myID + '\'' +
                ", myRatings=" + myRatings +
                '}';
    }
}
