package com.niit.dao.dao;

import com.niit.dao.model.Movie;

import java.util.List;

public interface MovieDao {
    public boolean saveMovie(Movie movie);

    public List<Movie> getAllMovie();

    public boolean deleteMovie(int movieId);

    Movie getMovieById(int id);



}
