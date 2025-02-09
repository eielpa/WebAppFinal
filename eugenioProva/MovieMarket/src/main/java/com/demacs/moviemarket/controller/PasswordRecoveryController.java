package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recupero-password")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    // Endpoint per inviare la richiesta di recupero password
    @PostMapping("/richiesta")
    public ResponseEntity<String> requestPasswordReset(@RequestBody String email) {
        String result = passwordRecoveryService.requestPasswordReset(email);
        if (result.contains("non trovata")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint per resettare la password
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetRequest) {
        String result = passwordRecoveryService.resetPassword(resetRequest.getToken(), resetRequest.getNewPassword());
        if (result.contains("non valido")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
