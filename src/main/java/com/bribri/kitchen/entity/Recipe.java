package com.bribri.kitchen.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="recipe")
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="favorite_ind")
    private boolean favoriteInd;

    @Column(name="image_url")
    private String imageUrl;

    @OneToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name="category_id")
    Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Step> steps = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(String name, String description, boolean favoriteInd, String imageUrl, Category category) {
        this.name = name;
        this.description = description;
        this.favoriteInd = favoriteInd;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Recipe() {
    }
}
