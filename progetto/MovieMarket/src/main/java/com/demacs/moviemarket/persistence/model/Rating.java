package com.demacs.moviemarket.persistence.model;

public class Rating {
    private int id;
    private String movieId;  // Titolo del film
    private String userId;   // Username dell'utente
    private double rating;

    // Costruttori
    public Rating() {}

    public Rating(int id, String movieId, String userId, double rating) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

