package com.movies.app.Movies.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.movies.app.Movies.dao.UserRepo;
import com.movies.app.Movies.model.MovieUserDetails;
import com.movies.app.Movies.model.User;

@Service
public class MovieUserDetailsService implements UserDetailsService{
  
  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepo.findByUsername(username);
    
    user.orElseThrow(() -> new UsernameNotFoundException("No User object found for: " + username));
    //Get the User instance from the Optional
    return user.map(MovieUserDetails::new).get();
  }

}
