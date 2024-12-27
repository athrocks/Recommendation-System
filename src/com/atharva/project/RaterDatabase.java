package com.atharva.project;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {

    //                  < raterId, Rater >
    // Rater object that includes all the movie ratings made by this rater.
    private static HashMap<String, Rater> ourRaters;

    private static void initialize() {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap<String, Rater>();
            addRatings("src/com/atharva/project/data/" + filename);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for (CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id, item, Double.parseDouble(rating));
        }

    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);
    }

    // returns a Rater that has this ID.
    public static Rater getRater(String id) {
        initialize();

        return ourRaters.get(id);
    }

    // returns an ArrayList of Raters from the database
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<>(ourRaters.values());

        return list;
    }

    // returns the number of raters in the database
    public static int size() {
        return ourRaters.size();
    }


}