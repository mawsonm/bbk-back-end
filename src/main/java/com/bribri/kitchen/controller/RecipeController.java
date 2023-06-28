package com.bribri.kitchen.controller;

import com.bribri.kitchen.dao.*;
import com.bribri.kitchen.dto.RecipeGridDto;
import com.bribri.kitchen.entity.*;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @PostMapping(path="addRecipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe addRecipe(@RequestBody Recipe recipe) {
            //List<Recipe> drinks = findRecipes(recipe.getDrink().getId());
            Recipe recipeDto = new Recipe(recipe.getName(), recipe.getDescription(), recipe.isFavoriteInd(), recipe.getImageUrl(), recipe.getCategory(), recipe.getTimeToCook(), recipe.getDrinkPairing());
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
    public Recipe findRecipes(@PathVariable("id") Integer id){
        System.out.println(id);
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
            return recipe.get();
        }
        return new Recipe();
    }

    @Transactional
    @DeleteMapping(path = "recipe/{id}")
    public String deleteRecipe(@PathVariable Integer id){
        recipeRepository.deleteById(id);
        return "Recipe with id " + id + " deleted successfully!";
    }

    @Transactional
    @PutMapping(path="updateRecipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe){
        Recipe recipeFromDB = recipeRepository.getById(recipe.getId());
        System.out.println(recipeFromDB.toString());
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
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(name);
        return transformRecipes(recipes);
    }

    @GetMapping(path="recipe/all")
    public List<RecipeGridDto> findAllRecipes(){
        return recipeRepository.findAll().stream().map(rec -> new RecipeGridDto(rec)).collect(Collectors.toList());
    }

    @GetMapping(path="drink")
    public List<RecipeGridDto> findDrinkByName(@Param("name") String name){
        System.out.println(name);
        List<Recipe> recipes = recipeRepository.findDrinkByName(name);
        return transformRecipes(recipes);
    }

    @GetMapping(path="recipe/category")
    public List<RecipeGridDto> findRecipeByCategoryName(@Param("name") String name){
        System.out.println(name);
        Category category = categoryRepository.findByName(name);
        List<Recipe> recipes = recipeRepository.findByCategory(category);

        return transformRecipes(recipes);
    }

    @GetMapping(path="recipe/ingredient")
    public Set<RecipeGridDto> findRecipeByIngredients(@Param("name") String name){
        String[] ingredients = name.split(",");
        Set<Recipe> recipeSet = new HashSet<>();
        List<Recipe> recipeList = new ArrayList<>();
        for(String ingredient : ingredients){
            List<Ingredient> ingredientsFromDB = ingredientRepository.findByNameContainingIgnoringCase(ingredient);
            List<Recipe> recipes = ingredientsFromDB.stream().map(Ingredient::getRecipe).collect(Collectors.toList());
            recipeSet.addAll(recipes);
            recipeList.addAll(recipes);
        }
        Set<RecipeGridDto> gridRecipes = new HashSet<>();
        for(Recipe recipe : recipeSet){
            List<Integer> numOccurences = IntStream.range(0, recipeList.size())
                    .filter(i -> Objects.equals(recipe, recipeList.get(i))).boxed().collect(Collectors.toList());
            RecipeGridDto gridDto = new RecipeGridDto(recipe, numOccurences.size(), recipe.getIngredients().size());
            gridRecipes.add(gridDto);
        }
        return gridRecipes;
    }

    private List<RecipeGridDto> transformRecipes(List<Recipe> recipes){
        List<RecipeGridDto> recipesGrid = new ArrayList<>();
        for(Recipe recipe : recipes){
            recipesGrid.add(new RecipeGridDto(recipe));
        }
        return recipesGrid;
    }

}
