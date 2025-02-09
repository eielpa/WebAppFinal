package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.service.PersonalLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download")
@CrossOrigin(origins = "http://localhost:4200")
public class DownloadController {

    @Autowired
    private PersonalLibraryService personalLibraryService;

    @GetMapping
    public ResponseEntity<Resource> downloadMovie(@RequestParam String userId, @RequestParam String movieId) {
        // Registra il download inserendo una nuova riga nel DB
        personalLibraryService.recordDownload(userId, movieId);

        // Carica il file dalla cartella resources/static
        Resource resource = new ClassPathResource("static/provaFilm.txt");
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Prepara le intestazioni per forzare il download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=provaFilm.txt");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}