package com.bribri.kitchen.dao;

import com.bribri.kitchen.dto.RecipeGridDto;
import com.bribri.kitchen.entity.Category;
import com.bribri.kitchen.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "recipe", path = "recipe")
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(concat('%', :name, '%')) AND r.category_id=11", nativeQuery = true)
    List<Recipe> findDrinkByName(@Param("name") String name);

    List<Recipe> findByCategory(Category category);
}
