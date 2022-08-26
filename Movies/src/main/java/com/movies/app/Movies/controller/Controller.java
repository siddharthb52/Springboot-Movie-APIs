package com.movies.app.Movies.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.movies.app.Movies.dao.MovieRepo;
import com.movies.app.Movies.model.Movie;
import com.movies.app.Movies.service.MovieService;
import com.movies.app.Movies.service.MovieServiceImpl;

@RestController
public class Controller {

  @Autowired
  private MovieService movieService;

  @GetMapping(value = "/")
  public String getPage() {
    return "<h1>Local Host Page Accessed.</h1>";
  }
  


  // GetMapping annotation for displaying all movies at the url with /movies
  @GetMapping(value = "/movies")
  public ResponseEntity<List<Movie>> getAllMovies() {
    try {
      // Get list of all movies via Service class
      List<Movie> allMovies = movieService.getAllMovies();

      // Check that the list of movies is not empty
      if (allMovies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // Return the response entity associated with the method
      return new ResponseEntity<>(allMovies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // PostMapping annotation for saving Movies at the same url as the GetMapping annotated method
  @PostMapping(value = "/movies")
  public ResponseEntity<Movie> createMovie(@RequestBody Movie mv) {
    try {

      // If the movie provided is null, return the corresponding HTTP error
      if (mv == null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // Otherwise, create the movie and add it to the database
      Movie newMovie = movieService.createMovie(mv.getName(), mv.getDescription(), mv.getRating());

      // Return the corresponding response entity
      // TODO confirm that the ResponseEntity can take null as a parameter

      System.out.println(newMovie.getId());
      return new ResponseEntity<>(newMovie, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GetMapping annotation for displaying all movies at the URL with a given name after "movies/"
  @GetMapping("/movies/name/{name}")
  public ResponseEntity<List<Movie>> getMovieByName(@PathVariable("name") String name) {
    try {
      List<Movie> movies = movieService.getByName(name);

      // Check that the list of movies is not empty
      if (movies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      // Return the response entity associated with the method
      return new ResponseEntity<>(movies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GetMapping annotation for displaying all movies at the URL with a given rating after
  // "movies/rating"
  @GetMapping("/movies/rating/{rating}")
  public ResponseEntity<List<Movie>> getMovieByRating(@PathVariable("rating") double rating) {
    try {

      List<Movie> movies = movieService.getByRating(rating);

      // Check that the list of movies is not empty
      if (movies.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // Return the response entity associated with the method
      return new ResponseEntity<>(movies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(value = "/movies")
  public ResponseEntity<Movie> updateMovie(@RequestBody Movie mv) {
    try {

      // If the movie provided is null, return the corresponding HTTP error
      if (mv == null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      // Check if there is an identically named movie
      if (movieService.updateMovie(mv.getName(), mv)) {
        return new ResponseEntity<>(mv, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(value = "/movies/delete/{uuid}")
  public ResponseEntity<Movie> deleteMovie(@PathVariable("uuid") UUID id) {
    try {

      System.out.println(id);
      // If the movie provided is null, return the corresponding HTTP error
      if (movieService.delete(id)) {
        return new ResponseEntity<>(null, HttpStatus.OK);
      } else {
        // Not Found http error if the Movie to be deleted doesn't exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      // In case of NullPointer Exception from the delete method in the MovieService class
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // GetMapping annotation for displaying all movies at the URL with a given name after "movies/"
  @GetMapping("/moviesByID/{id}")
  public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable("id") UUID id) {
    try {
      Optional<Movie> movies = movieService.getById(id);
      System.out.println(movies);

      // Check that the list of movies is not empty
      if (!movies.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      // Return the response entity associated with the method
      return new ResponseEntity<>(movies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/movies/update/{id}")
  public ResponseEntity<Optional<Movie>> updateMovie(@PathVariable("id") UUID id, @RequestBody Movie mv) {
    try {
      String name = mv.getName();
      String description = mv.getDescription();
      double rating = mv.getRating();
      Optional<Movie> movie = movieService.updateById(id, name, description, rating);

      // Check that the list of movies is not empty
      if (movie == null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      // Return the response entity associated with the method
      return new ResponseEntity<>(movie, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



}
