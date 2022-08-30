package com.bribri.kitchen.dao;

import com.bribri.kitchen.dto.RecipeGridDto;
import com.bribri.kitchen.entity.Category;
import com.bribri.kitchen.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "recipe", path = "recipe")
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByNameContaining(String name);

    List<Recipe> findByCategory(Category category);
}
