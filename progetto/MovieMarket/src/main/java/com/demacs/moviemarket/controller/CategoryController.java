package com.demacs.moviemarket.controller;

import com.demacs.moviemarket.persistence.model.Category;
import com.demacs.moviemarket.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getNameById/{id}")
    public ResponseEntity<String> getCategory(@PathVariable int id) {
        try {
            String categoryName = categoryService.findCategoryNameById(id);
            System.out.println(categoryName);

            if (categoryName == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria non trovata");
            }

            return ResponseEntity.ok(categoryName);  // Restituisce direttamente una stringa
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore del server");
        }
    }



    @GetMapping("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("idByGenre")
    public int getIDByGenre(@RequestParam String genre) {
        return categoryService.findByGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setId(id);  // Set the ID to ensure it updates the correct category
        categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        Category category = categoryService.findById(id);
        categoryService.delete(category);
    }
}
