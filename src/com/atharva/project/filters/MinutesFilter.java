package com.atharva.project.filters;

import com.atharva.project.MovieDatabase;

public class MinutesFilter implements Filter {

    private int minMinutes;
    private int maxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {

        int movieMinutes = MovieDatabase.getMinutes(id);
        return movieMinutes >= minMinutes && movieMinutes <= maxMinutes;
    }
}
