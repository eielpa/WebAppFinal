package com.demacs.moviemarket.persistence.model;

import java.time.LocalDateTime;

public class Movie {

    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private Category category;
    private Integer rating; // Usiamo Integer per permettere valori null
    private LocalDateTime addedDate; // Data di aggiunta

    // Getter e setter per id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e setter per title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter e setter per description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter e setter per releaseYear
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    // Getter e setter per categoryId
    public int getCategoryId() {
        return category.getId();
    }

    public void setCategoryId(int categoryId) {
        if (this.category == null) {
            this.category = new Category();
        }
        this.category.setId(categoryId);
    }

    // Getter e setter per rating
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating != null && (rating < 0 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.rating = rating;
    }

    // Getter e setter per addedDate
    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
