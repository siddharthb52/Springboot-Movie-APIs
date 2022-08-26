package com.movies.app.Movies.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.movies.app.Movies.model.Movie;

public interface MovieService {
  
  public List<Movie> getAllMovies();
  public Optional<Movie> getMovie(String id);
  public List<Movie> getByName(String name);
  public List<Movie> getByRating(double rating);
  public Movie createMovie(String name, String description, double rating);
  public boolean updateMovie(String name, Movie mv);
  public boolean delete(UUID id);
  public void deleteAllMovies();
  public Optional<Movie> getById(UUID id);
  public Optional<Movie> updateById(UUID id, String name, String description, double rating);
}
