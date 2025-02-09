package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.MovieDao;
import com.demacs.moviemarket.persistence.dao.PersonalLibraryDao;
import com.demacs.moviemarket.persistence.model.Movie;
import com.demacs.moviemarket.persistence.model.PersonalLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonalLibraryService {

    @Autowired
    private MovieDao movieDao;
    private final JdbcTemplate jdbcTemplate;
    private final PersonalLibraryDao personalLibraryDao;

    public PersonalLibraryService(JdbcTemplate jdbcTemplate, PersonalLibraryDao personalLibraryDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.personalLibraryDao = personalLibraryDao;
    }

    public PersonalLibrary findById(int id) {
        return personalLibraryDao.findById(id);
    }

    public List<PersonalLibrary> findAll() {
        return personalLibraryDao.findAll();
    }

    public void save(PersonalLibrary personalLibrary) {
        personalLibraryDao.save(personalLibrary);
    }

    public void update(PersonalLibrary personalLibrary) {
        personalLibraryDao.update(personalLibrary);
    }

    public void delete(PersonalLibrary personalLibrary) {
        personalLibraryDao.delete(personalLibrary);
    }

    public List<Movie> getMoviesByUser(String userNickname) {
        return movieDao.findByUserNickname(userNickname);
    }

    public void addMovieToLibrary(String userNickname, String movieTitle) {
        // Trova il film in base al titolo
        Movie movie = movieDao.findByTitle(movieTitle);
        if (movie == null) {
            throw new RuntimeException("Film non trovato con titolo: " + movieTitle);
        }
        // Converti l'id del film in stringa (perché movie_id è di tipo varchar)
        String movieId = String.valueOf(movie.getId());

        // Controlla se la coppia (user_id, movie_id) esiste già
        String checkExistenceSql = "SELECT COUNT(*) FROM personal_library WHERE user_id = ? AND movie_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkExistenceSql, Integer.class, userNickname, movieId);

        // Se non esiste già, esegui l'inserimento
        if (count != null && count == 0) {
            // Impostiamo anche download_date e download_status se necessario
            String insertMovieSql = "INSERT INTO personal_library (user_id, movie_id, download_date, download_status) VALUES (?, ?, NOW(), 'DOWNLOADED')";
            jdbcTemplate.update(insertMovieSql, userNickname, movieId);
        }
    }

    public void recordDownload(String userId, String movieId) {
        String insertSql = "INSERT INTO personal_library (user_id, movie_id, download_date, download_status) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP, ?) " +
                "ON CONFLICT (user_id, movie_id) DO NOTHING";
        jdbcTemplate.update(insertSql, userId, movieId, "DOWNLOADED");
    }

}