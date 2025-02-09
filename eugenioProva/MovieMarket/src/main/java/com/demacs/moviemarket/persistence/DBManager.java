package com.demacs.moviemarket.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import com.demacs.moviemarket.persistence.dao.*;
import com.demacs.moviemarket.persistence.dao.postgres.*;
import org.springframework.stereotype.Component;

@Component
public class DBManager {
    private static DBManager instance = null;
    private final String url;
    private final String username;
    private final String password;
    private Connection conn = null;

    private DBManager() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("File application.properties non trovato!");
            }

            Properties properties = new Properties();
            properties.load(input);
            this.url = properties.getProperty("spring.datasource.url");
            this.username = properties.getProperty("spring.datasource.username");
            this.password = properties.getProperty("spring.datasource.password");

        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento delle configurazioni del database", e);
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException("Errore di connessione al database", e);
            }
        }
        return conn;
    }

    // 🔹 Correggo i DAO: passo una Connection, non un DBManager!
    public MovieDao getMovieDao() {
        return new MovieDaoPostgres(getInstance());
    }

    public CategoryDao getCategoryDao() {
        return new CategoryDaoPostgres(getInstance());
    }

    public DownloadDao getDownloadDao() {
        return new DownloadDaoPostgres(getInstance());
    }

    public PersonalLibraryDao getPersonalLibraryDao() {
        return new PersonalLibraryDaoPostgres(getInstance());
    }

    public UserDao getUserDao() {
        return new UserDaoPostgres(getInstance());
    }
}
