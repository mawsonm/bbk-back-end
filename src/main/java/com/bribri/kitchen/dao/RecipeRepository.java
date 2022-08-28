package com.bribri.kitchen.dao;

import com.bribri.kitchen.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recipe", path = "recipe")
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
