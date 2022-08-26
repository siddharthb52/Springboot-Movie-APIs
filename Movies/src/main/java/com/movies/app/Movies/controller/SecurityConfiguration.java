package com.movies.app.Movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.movies.app.Movies.service.MovieUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Using .httpBasic() in order to make Basic Auth with Postman possible
    http.httpBasic().and().authorizeRequests()
        // .antMatchers("/admin").hasRole("ADMIN")
        // .antMatchers("/user").hasAnyRole("ADMIN", "USER")
        // .antMatchers("/").permitAll()
        .antMatchers("/").hasRole("ADMIN")
        .antMatchers("/movies").hasRole("ADMIN")
        .and()
        .formLogin()
        .permitAll().and().logout().permitAll().and().httpBasic();
        http.cors().disable().csrf().disable();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
