package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.Rating;
import java.util.List;
import java.util.Optional;

public interface RatingDao {
    // Funzione per aggiungere una valutazione
    void save(Rating rating);

    // Funzione per recuperare una valutazione di un utente per un film
    Optional<Rating> getUserRatingForMovie(String userId, String movieId);

    // Funzione per ottenere tutte le valutazioni per un film
    List<Rating> getRatingsByMovie(String movieId);

    // Funzione per ottenere la valutazione media di un film
    Optional<Double> getAverageRatingByMovie(String movieId);

    // Funzione per aggiornare una valutazione
    void update(Rating rating);

    // Funzione per eliminare una valutazione di un utente per un film
    void delete(String userId, String movieId);

    // Funzione per ottenere tutte le valutazioni
    List<Rating> getAllRatings();
}
