package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.RatingDao;
import com.demacs.moviemarket.persistence.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingDao ratingDao;

    @Autowired
    public RatingService(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    // Salva una nuova valutazione
    public void saveRating(Rating rating) {
        ratingDao.save(rating);
    }

    // Ottiene la valutazione di un utente per un film
    public Optional<Rating> getUserRatingForMovie(String userId, String movieId) {
        return ratingDao.getUserRatingForMovie(userId, movieId);
    }

    // Ottiene tutte le valutazioni per un film
    public List<Rating> getRatingsByMovie(String movieId) {
        return ratingDao.getRatingsByMovie(movieId);
    }

    // Ottiene la valutazione media di un film
    public Optional<Double> getAverageRatingByMovie(String movieId) {
        return ratingDao.getAverageRatingByMovie(movieId);
    }

    // Aggiorna una valutazione
    public void updateRating(Rating rating) {
        ratingDao.update(rating);
    }

    // Elimina una valutazione di un utente per un film
    public void deleteRating(String userId, String movieId) {
        ratingDao.delete(userId, movieId);
    }

    // Ottiene tutte le valutazioni
    public List<Rating> getAllRatings() {
        return ratingDao.getAllRatings();
    }
}
