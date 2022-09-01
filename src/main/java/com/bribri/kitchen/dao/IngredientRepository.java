package com.bribri.kitchen.dao;

import com.bribri.kitchen.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameContainingIgnoringCase(String name);
    //@Query("select count(i) from Ingredient i where i.id = ?1")
    int countByRecipeId(int id);
}
