package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.persistence.model.Movie;
import com.demacs.moviemarket.persistence.model.PersonalLibrary;
import com.demacs.moviemarket.service.PersonalLibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personal-library")
public class PersonalLibraryController {

    private final PersonalLibraryService personalLibraryService;

    public PersonalLibraryController(PersonalLibraryService personalLibraryService) {
        this.personalLibraryService = personalLibraryService;
    }

    @GetMapping("/user/{nickname}")
    public List<Movie> getUserLibrary(@PathVariable String nickname) {
        return personalLibraryService.getMoviesByUser(nickname);
    }

    @GetMapping
    public List<PersonalLibrary> getAllPersonalLibraries() {
        return personalLibraryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPersonalLibrary(@RequestBody PersonalLibrary personalLibrary) {
        personalLibraryService.save(personalLibrary);
    }

    @PutMapping("/{id}")
    public void updatePersonalLibrary(@PathVariable int id, @RequestBody PersonalLibrary personalLibrary) {
        personalLibrary.setId(id);  // Set the ID to ensure it updates the correct entry
        personalLibraryService.update(personalLibrary);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalLibrary(@PathVariable int id) {
        PersonalLibrary personalLibrary = personalLibraryService.findById(id);
        personalLibraryService.delete(personalLibrary);
    }

    @PostMapping("/add")
    public String addMovieToLibrary(@RequestBody Map<String, String> request) {
        String userNickname = request.get("userNickname");
        String movieTitle = request.get("movieTitle");

        personalLibraryService.addMovieToLibrary(userNickname, movieTitle);
        return "Film aggiunto con successo alla libreria di " + userNickname;
    }
}