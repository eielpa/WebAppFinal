package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.MovieDao;
import com.demacs.moviemarket.persistence.dao.MovieDaoProxy;
import com.demacs.moviemarket.persistence.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = new MovieDaoProxy(movieDao);
    }

    public Movie findById(int id) {
        return movieDao.findById(id);
    }

    public Movie findByTitle(String title) {
        return movieDao.findByTitle(title);
    }

    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    public void update(Movie movie) {
        movieDao.update(movie);
    }

    public void delete(Movie movie) {
        movieDao.delete(movie);
    }

    public List<Movie> findByRating(int rating) {
        return movieDao.findByRating(rating);
    }

    public List<Movie> findByCategory(int categoryId) {
        return movieDao.findByCategory(categoryId);
    }

    public List<Movie> findMostRecent(int limit) {
        return movieDao.findMostRecent(limit);
    }

    public boolean save(Movie movie) { return movieDao.save(movie); }
}
