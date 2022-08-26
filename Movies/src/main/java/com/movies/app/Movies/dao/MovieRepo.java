package com.movies.app.Movies.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.movies.app.Movies.model.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, UUID>{
  
  List<Movie> findByName(String name);
  
  List<Movie> findByRating(double rating);
  
  Optional<Movie> findById(UUID id);

}
