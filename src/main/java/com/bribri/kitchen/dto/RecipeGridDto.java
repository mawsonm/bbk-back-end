package com.bribri.kitchen.dto;

import com.bribri.kitchen.entity.Category;
import com.bribri.kitchen.entity.Recipe;

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

    private int numIngredients;

    private int totalIngredients;

    private Recipe drinkPairing;
    public Recipe getDrinkPairing() {
        return drinkPairing;
    }

    public int getTotalIngredients() {
        return totalIngredients;
    }

    public void setTotalIngredients(int totalIngredients) {
        this.totalIngredients = totalIngredients;
    }


    public int getNumIngredients() {
        return numIngredients;
    }

    public void setNumIngredients(int numIngredients) {
        this.numIngredients = numIngredients;
    }

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

    public RecipeGridDto(Recipe recipe){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.favoriteInd = recipe.isFavoriteInd();
        this.imageUrl = recipe.getImageUrl();
        this.category = recipe.getCategory();
        this.description = recipe.getDescription();
        this.drinkPairing = recipe.getDrink();
    }

    public RecipeGridDto(Recipe recipe, int numIngredients, int totalIngredients){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.favoriteInd = recipe.isFavoriteInd();
        this.imageUrl = recipe.getImageUrl();
        this.category = recipe.getCategory();
        this.description = recipe.getDescription();
        this.drinkPairing = recipe.getDrink();
        this.numIngredients = numIngredients;
        this.totalIngredients = totalIngredients;
    }
}
