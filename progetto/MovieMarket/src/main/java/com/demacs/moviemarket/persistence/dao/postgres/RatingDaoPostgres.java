package com.demacs.moviemarket.persistence.dao.postgres;

import com.demacs.moviemarket.persistence.dao.RatingDao;
import com.demacs.moviemarket.persistence.model.Rating;
import com.demacs.moviemarket.persistence.DBManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RatingDaoPostgres implements RatingDao {
    private final Connection conn;

    public RatingDaoPostgres(DBManager dbManager) {
        // Otteniamo la connessione una sola volta nel costruttore
        this.conn = dbManager.getConnection();
    }

    @Override
    public void save(Rating rating) {
        String query = "INSERT INTO ratings (user_id, movie_id, rating) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, rating.getUserId());
            stmt.setString(2, rating.getMovieId());
            stmt.setDouble(3, rating.getRating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiungere la valutazione", e);
        }
    }

    @Override
    public Optional<Rating> getUserRatingForMovie(String userId, String movieId) {
        String query = "SELECT * FROM ratings WHERE user_id = ? AND movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setString(2, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Rating rating = new Rating();
                    rating.setUserId(rs.getString("user_id"));
                    rating.setMovieId(rs.getString("movie_id"));
                    rating.setRating(rs.getDouble("rating"));
                    return Optional.of(rating);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare la valutazione dell'utente", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Rating> getRatingsByMovie(String movieId) {
        String query = "SELECT * FROM ratings WHERE movie_id = ?";
        List<Rating> ratings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rating rating = new Rating();
                    rating.setUserId(rs.getString("user_id"));
                    rating.setMovieId(rs.getString("movie_id"));
                    rating.setRating(rs.getDouble("rating"));
                    ratings.add(rating);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare le valutazioni per il film", e);
        }
        return ratings;
    }

    @Override
    public Optional<Double> getAverageRatingByMovie(String movieId) {
        String query = "SELECT AVG(rating) AS average_rating FROM ratings WHERE movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getDouble("average_rating"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare la valutazione media per il film", e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Rating rating) {
        String query = "UPDATE ratings SET rating = ? WHERE user_id = ? AND movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, rating.getRating());
            stmt.setString(2, rating.getUserId());
            stmt.setString(3, rating.getMovieId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornare la valutazione", e);
        }
    }

    @Override
    public void delete(String userId, String movieId) {
        String query = "DELETE FROM ratings WHERE user_id = ? AND movie_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setString(2, movieId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'eliminare la valutazione", e);
        }
    }

    @Override
    public List<Rating> getAllRatings() {
        String query = "SELECT * FROM ratings";
        List<Rating> ratings = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setUserId(rs.getString("user_id"));
                rating.setMovieId(rs.getString("movie_id"));
                rating.setRating(rs.getDouble("rating"));
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nel recuperare tutte le valutazioni", e);
        }
        return ratings;
    }
}
