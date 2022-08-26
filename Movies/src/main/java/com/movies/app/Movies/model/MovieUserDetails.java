package com.movies.app.Movies.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MovieUserDetails implements UserDetails {

  private String username;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public MovieUserDetails(String username) {
    this.username = username;
  }


  public MovieUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.active = user.isActive();
    //Get a stream by splitting with a comma as the regular expression, then convert to a List
    this.authorities = Arrays.stream(user.getRoles().split(","))
         .map(SimpleGrantedAuthority::new)
         .collect(Collectors.toList());
  }

  public MovieUserDetails() {
    //
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  public boolean isAccountNonLocked() {
    return true;
  }


  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return active;
  }


}
