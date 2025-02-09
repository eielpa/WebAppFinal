package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.UserDao;
import com.demacs.moviemarket.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordRecoveryService {

    @Autowired
    private UserDao userDao;

    // Metodo per inviare la richiesta di recupero password
    public String requestPasswordReset(String email) {
        // Trova l'utente tramite email
        User user = userDao.findByEmail(email);

        if (user == null) {
            return "Email non trovata.";
        }

        // Genera un token di reset
        String resetToken = UUID.randomUUID().toString();

        // Aggiorna il token nel database
        user.setPasswordResetToken(resetToken);
        userDao.update(user);

        // Link di reset, qui come esempio, in un'app reale dovresti inviarlo via email
        String resetLink = "http://tuosito.com/reset-password?token=" + resetToken;

        return "Istruzioni inviate via email. Link di reset: " + resetLink;
    }

    // Metodo per resettare la password
    public String resetPassword(String token, String newPassword) {
        // Trova l'utente tramite il token
        User user = userDao.findByEmail(token);
        if (user == null || user.getPasswordResetToken() == null || !user.getPasswordResetToken().equals(token)) {
            return "Token non valido o scaduto.";
        }

        // Aggiorna la password
        user.setPassword(newPassword);
        user.setPasswordResetToken(null); // Rimuovi il token
        userDao.update(user);

        return "Password cambiata con successo.";
    }
}
