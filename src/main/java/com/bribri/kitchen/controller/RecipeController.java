package com.bribri.kitchen.controller;

import com.bribri.kitchen.dao.*;
import com.bribri.kitchen.dto.RecipeGridDto;
import com.bribri.kitchen.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Array;
import java.util.*;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    StepRepository stepRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    @PostMapping(path="recipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe addRecipe(@RequestBody Recipe recipe) {
            Recipe recipeDto = new Recipe(recipe.getName(), recipe.getDescription(), recipe.isFavoriteInd(), recipe.getImageUrl(), recipe.getCategory());
            Recipe rec = recipeRepository.save(recipeDto);

            for(Step step : recipe.getSteps()){
                step.setRecipe(rec);
            }
            List<Step> steps = stepRepository.saveAll(recipe.getSteps());

            for(Ingredient ingredient : recipe.getIngredients()){
                ingredient.setRecipe(rec);
            }
            List<Ingredient> ingredients =  ingredientRepository.saveAll(recipe.getIngredients());

            rec.setSteps(new HashSet<>(steps));
            rec.setIngredients(new HashSet<>(ingredients));

            return rec;
    }

    @GetMapping("recipe/{id}")
    public List<Recipe> findRecipes(@Param("id") Integer id){
        if(id == null){
            return recipeRepository.findAll();
        }
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
            List<Recipe> recipes = new ArrayList<>();
            recipes.add(recipe.get());
            return recipes;
        }
        return new ArrayList<>();
    }

    @Transactional
    @DeleteMapping(path = "recipe/{id}")
    public String deleteRecipe(@PathVariable Integer id){
        recipeRepository.deleteById(id);
        return "Recipe with id " + id + " deleted successfully!";
    }

    @Transactional
    @PutMapping(path="recipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe){
        Recipe recipeFromDB = recipeRepository.getById(recipe.getId());
        recipeFromDB.setName(recipe.getName());
        recipeFromDB.setCategory(recipe.getCategory());
        recipeFromDB.setDescription(recipe.getDescription());
        recipeFromDB.setFavoriteInd(recipe.isFavoriteInd());
        recipeFromDB.setImageUrl(recipe.getImageUrl());
        for(Step step : recipe.getSteps()){
            step.setRecipe(recipeFromDB);
        }
        recipeFromDB.setSteps(recipe.getSteps());
        for(Ingredient ingredient : recipe.getIngredients()){
            ingredient.setRecipe(recipeFromDB);
        }
        recipeFromDB.setIngredients(recipe.getIngredients());
        return recipeRepository.save(recipeFromDB);
    }

    @GetMapping(path="recipe")
    public List<RecipeGridDto> findRecipeByName(@Param("name") String name){
        List<Recipe> recipes = recipeRepository.findByNameContaining(name);
        return transformRecipes(recipes);
    }

    @GetMapping(path="recipe/category")
    public List<RecipeGridDto> findRecipeByCategoryName(@Param("name") String name){
        System.out.println(name);
        Category category = categoryRepository.findByName(name);
        List<Recipe> recipes = recipeRepository.findByCategory(category);

        return transformRecipes(recipes);
    }

    private List<RecipeGridDto> transformRecipes(List<Recipe> recipes){
        List<RecipeGridDto> recipesGrid = new ArrayList<>();
        for(Recipe recipe : recipes){
            recipesGrid.add(new RecipeGridDto(recipe));
        }
        return recipesGrid;
    }

}
