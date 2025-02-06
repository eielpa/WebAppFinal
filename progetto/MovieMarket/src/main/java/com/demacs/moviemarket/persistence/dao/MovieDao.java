package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieDao {
    Movie findById(int id);

    Movie findByTitle(String title);
    List<Movie>findByUserNickname(String nickname);
    List<Movie> findAll();
    List<Movie> findByRating(int rating);
    List<Movie> findByCategory(int categoryId);
    List<Movie> findMostRecent(int limit);
    void update(Movie movie);
    void delete(Movie movie);
    boolean save(Movie movie);
}
