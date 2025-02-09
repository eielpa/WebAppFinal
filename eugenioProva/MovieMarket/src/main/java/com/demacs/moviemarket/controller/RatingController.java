package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.service.RatingService;
import com.demacs.moviemarket.persistence.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Aggiungi una valutazione
    @PostMapping
    public ResponseEntity<Void> addRating(@RequestBody Rating rating) {
        ratingService.saveRating(rating);
        return ResponseEntity.ok().build();
    }

    // Ottieni la valutazione di un utente per un film
    @GetMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Rating> getUserRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {
        Optional<Rating> rating = ratingService.getUserRatingForMovie(userId, movieId);
        return rating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ottieni tutte le valutazioni per un film
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getRatingsByMovie(@PathVariable String movieId) {
        List<Rating> ratings = ratingService.getRatingsByMovie(movieId);
        return ResponseEntity.ok(ratings);
    }

    // Ottieni la valutazione media per un film
    @GetMapping("/average/movie/{movieId}")
    public ResponseEntity<Double> getAverageRatingByMovie(@PathVariable String movieId) {
        Optional<Double> averageRating = ratingService.getAverageRatingByMovie(movieId);
        return averageRating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Aggiorna una valutazione
    @PutMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Void> updateRating(@PathVariable String userId, @PathVariable String movieId, @RequestBody Rating rating) {
        rating.setUserId(userId);
        rating.setMovieId(movieId);
        ratingService.updateRating(rating);
        return ResponseEntity.ok().build();
    }

    // Elimina una valutazione
    @DeleteMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String userId, @PathVariable String movieId) {
        ratingService.deleteRating(userId, movieId);
        return ResponseEntity.ok().build();
    }

    // Ottieni tutte le valutazioni
    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
}
