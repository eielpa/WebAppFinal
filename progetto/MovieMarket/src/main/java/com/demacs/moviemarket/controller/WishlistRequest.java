package com.demacs.moviemarket.controller;

public class WishlistRequest {
    private String userId;
    private String movieId;

    // Getter e setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}