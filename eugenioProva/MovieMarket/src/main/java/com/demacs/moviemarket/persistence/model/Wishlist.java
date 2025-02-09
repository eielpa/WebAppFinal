package com.demacs.moviemarket.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "movie_id", nullable = false)
    private String movieId;

    // Costruttori
    public Wishlist() { }

    public Wishlist(String userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public String getMovieId() {
        return movieId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    private String movieTitle;
    public String getMovieTitle() {
        return movieTitle;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

}