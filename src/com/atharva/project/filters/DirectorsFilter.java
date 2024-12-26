package com.atharva.project.filters;

import com.atharva.project.MovieDatabase;

public class DirectorsFilter implements Filter {

    String director; // multiple direcor

    public DirectorsFilter(String director) {
        this.director = director;
    }

    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        String[] directorArray = directors.split(",");

        for(String directorVar : directorArray){
            if (directorVar.equals(director)){
                return true;
            }
        }

        return false;
    }
}
