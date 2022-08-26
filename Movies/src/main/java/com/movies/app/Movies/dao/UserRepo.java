package com.movies.app.Movies.dao;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.movies.app.Movies.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
  
  Optional<User> findByUsername(String username);

}
