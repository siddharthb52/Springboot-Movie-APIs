package com.movies.app.Movies.model;

import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "movie")
public class Movie {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  @Type(type="uuid-char")
  private UUID id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private double rating;

  // 3-Variable Constructor for testing purposes
  public Movie(String name, String description, double rating) {
    this.name = name;
    this.description = description;
    this.rating = rating;
  }

  // Empty Constructor for use in the Service Impl Class
  public Movie() {
    //
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }


}
