package com.demacs.moviemarket.persistence.model;

public class PersonalLibrary {

    private int id;
    private User user;
    private Movie movie;
    private Download download;

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
        return download.getDownloadDate();
    }

    public void setDownloadDate(String downloadDate) {
        this.download.setDownloadDate(downloadDate);
    }

    // Getter e setter per downloadStatus
    public String getDownloadStatus() {
        return download.getDownloadStatus();
    }

    public void setDownloadStatus(String downloadStatus) {
        this.download.setDownloadStatus(downloadStatus);
    }
}
