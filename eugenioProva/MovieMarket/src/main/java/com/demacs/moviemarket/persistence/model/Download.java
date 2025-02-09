package com.demacs.moviemarket.persistence.model;

public class Download {

    private int id;
    private User user;
    private Movie movie;
    private String downloadDate;
    private String downloadStatus;

    // Getter e setter per id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e setter per userId
    public String getUserId() {
        return user.getId();
    }

    public void setUserId(String userId) {
        this.user.setId(userId);
    }

    // Getter e setter per movieId
    public int getMovieId() {
        return movie.getId();
    }

    public void setMovieId(int movieId) {
        this.movie.setId(movieId);
    }

    // Getter e setter per downloadDate
    public String getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(String downloadDate) {
        this.downloadDate = downloadDate;
    }

    // Getter e setter per downloadStatus
    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}