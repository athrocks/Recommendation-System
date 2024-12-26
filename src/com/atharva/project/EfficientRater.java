package com.atharva.project;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID; //  a unique String ID for this rater (raterId)
    // <movieId, Rating>
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

//    (movieId,ratingValue)
    public void addRating(String item, double rating) {
//        myRatings.add(new Rating(item,rating));

        Rating newRating = new Rating(item, rating);
        myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return true;
//            }
//        }

        if(myRatings.containsKey(item)) {
            return true;
        }

        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return myRatings.get(k).getValue();
//            }
//        }
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
