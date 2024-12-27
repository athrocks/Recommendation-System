package com.atharva.project;

import java.util.ArrayList;
import java.util.HashMap;

public interface Rater {

    public HashMap<String,Rating> getMyRatings();
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();

}
