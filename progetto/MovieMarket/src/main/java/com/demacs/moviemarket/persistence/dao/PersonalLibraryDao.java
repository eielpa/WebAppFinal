package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.PersonalLibrary;

import java.util.List;

public interface PersonalLibraryDao {
    PersonalLibrary findById(int id);
    List<PersonalLibrary> findAll();
    void save(PersonalLibrary personalLibrary);
    void update(PersonalLibrary personalLibrary);
    void delete(PersonalLibrary personalLibrary);
}
