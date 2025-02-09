package com.demacs.moviemarket.persistence.dao;

import com.demacs.moviemarket.persistence.model.Category;


import java.util.List;

public interface CategoryDao {
    Category findById(int id);

    int findByGenre(String genre);
    List<Category> findAll();
    void save(Category category);
    void update(Category category);
    void delete(Category category);


}
