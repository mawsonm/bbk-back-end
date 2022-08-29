package com.bribri.kitchen.dto;

import com.bribri.kitchen.entity.Category;

public class RecipeGridDto {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private boolean favoriteInd;
    private String imageUrl;
    private Category category;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavoriteInd() {
        return favoriteInd;
    }

    public void setFavoriteInd(boolean favoriteInd) {
        this.favoriteInd = favoriteInd;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeGridDto(int id, String name, boolean favoriteInd, String imageUrl, Category category, String description) {
        this.id = id;
        this.name = name;
        this.favoriteInd = favoriteInd;
        this.imageUrl = imageUrl;
        this.category = category;
        this.description = description;
    }
}
