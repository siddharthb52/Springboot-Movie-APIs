package com.movies.app.Movies.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movies.app.Movies.dao.MovieRepo;
import com.movies.app.Movies.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieRepo repo;

//Empty constructor for the Service class
  public MovieServiceImpl() {
    //
  }

  public List<Movie> getAllMovies() {
    return repo.findAll();
  }

  public Optional<Movie> getMovie(String id) {
    // Produce the corresponding UUID from the passed String
    UUID uu = UUID.fromString(id);
    // Return corresponding movies in database
    return repo.findById(uu);
  }

  public List<Movie> getByName(String name) {
    return repo.findByName(name);
  }

  public List<Movie> getByRating(double rating) {
    return repo.findByRating(rating);
  }
  
  public Optional<Movie> getById(UUID id){
    return repo.findById(id);
  }


  public Movie createMovie(String name, String description, double rating) {
    Movie mv = new Movie(name, description, rating);
    repo.save(mv);
    return mv;
  }
  
  public boolean updateMovie(String name, Movie mv){
    try {
    Movie old = repo.findByName(name).get(0);
    old.setDescription(mv.getDescription());
    old.setRating(mv.getRating());
    return true;
    }catch(Exception e) {
      return false;
    }
  }

  public boolean delete(UUID id) {
    if(repo.existsById(id)) {
      repo.deleteById(id);
      return true;
    }
    System.out.println("ID not found: " + id);
    return false;
  }
  
  public void deleteAllMovies() {
    repo.deleteAll();   
  }
  
  public Optional<Movie> updateById(UUID id, String name, String description, double rating){
    Optional<Movie> opt = getById(id);
    if(opt.isPresent()) {
      Movie mv = opt.get();
      mv.setName(name);
      mv.setDescription(description);
      mv.setRating(rating);
      repo.save(mv);
      return Optional.of(mv);
    }
    return null;    
    
  }
}
