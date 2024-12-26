package com.atharva.project.filters;

import com.atharva.project.MovieDatabase;

public class GenreFilter implements Filter {

    private String  genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        String genres = MovieDatabase.getGenres(id);
        String[] genreArray = genres.split(", ");

        for(String genreVar : genreArray){
            if (genreVar.equals(genre)){
                return true;
            }
        }

        return false;
    }
}
