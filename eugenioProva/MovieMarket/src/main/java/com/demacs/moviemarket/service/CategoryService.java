package com.demacs.moviemarket.service;

import com.demacs.moviemarket.persistence.dao.CategoryDao;
import com.demacs.moviemarket.persistence.model.Category;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public String findCategoryNameById(int id) {
        Category category = findById(id);  // Trova la categoria per ID
        return category.getName();  // Restituisce il nome della categoria
    }


    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    public int findByGenre(String genre) {
        return categoryDao.findByGenre(genre);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void save(Category category) {
        categoryDao.save(category);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    public void delete(Category category) {
        categoryDao.delete(category);
    }
}
