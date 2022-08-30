package com.bribri.kitchen.dao;

import com.bribri.kitchen.entity.Category;
import com.bribri.kitchen.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name = ?1")
    Category findByName(String name);
}
