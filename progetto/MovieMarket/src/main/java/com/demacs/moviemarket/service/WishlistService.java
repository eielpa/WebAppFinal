package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Aggiunge un film alla wishlist dell'utente. Lancia un'eccezione se già presente.
    public Wishlist addToWishlist(String userId, String movieId) {
        // Modifica la query per ottenere il conteggio senza usare metodi deprecati
        String checkQuery = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND movie_id = ?";

        // Usa queryForRowSet che non è deprecato per ottenere il risultato
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(checkQuery, userId, movieId);

        // Verifica se la query ha restituito un risultato
        int count = 0;
        if (rowSet.next()) {
            count = rowSet.getInt(1); // Ottieni il conteggio dalla prima colonna
        }

        if (count > 0) {
            throw new RuntimeException("Film già presente nella wishlist.");
        }

        // Inserisci il nuovo film nella wishlist
        String insertQuery = "INSERT INTO wishlist (user_id, movie_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"id"});
            ps.setString(1, userId);
            ps.setString(2, movieId);
            return ps;
        }, keyHolder);

        // Verifica se la chiave generata è null
        Long id = keyHolder.getKey() != null ? keyHolder.getKey().longValue() : 0L; // Imposta un ID di default (0L) se è null

        // Crea e restituisci l'oggetto Wishlist
        Wishlist wishlist = new Wishlist();
        wishlist.setId(id);
        wishlist.setUserId(userId);
        wishlist.setMovieId(movieId);
        return wishlist;
    }



    // Rimuove un elemento dalla wishlist tramite il suo ID
    public void removeFromWishlist(Long movieId) {
        String deleteQuery = "DELETE FROM wishlist WHERE id = ?";
        jdbcTemplate.update(deleteQuery, movieId);
    }

    // Recupera tutti gli elementi della wishlist per un dato userId
    public List<Wishlist> getWishlistByUser(String userId) {
        String selectQuery = """
        SELECT w.id, w.user_id, w.movie_id\s
        FROM wishlist w\s
        JOIN movies m ON w.movie_id = m.title
        WHERE w.user_id = ?
   \s""";

        // Usa queryForRowSet per ottenere i risultati
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(selectQuery, userId);

        List<Wishlist> wishlist = new ArrayList<>();
        while (rowSet.next()) {
            Wishlist w = new Wishlist();
            w.setId(rowSet.getLong("id"));
            w.setUserId(rowSet.getString("user_id"));
            w.setMovieId(rowSet.getString("movie_id"));
            wishlist.add(w);
        }
        return wishlist;
    }




}