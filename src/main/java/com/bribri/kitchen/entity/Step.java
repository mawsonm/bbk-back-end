package com.bribri.kitchen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="step")
@Getter
@Setter
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="number")
    private String number;

    @Column(name="description")
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    public Step(String number, String description, Recipe recipe) {
        this.number = number;
        this.description = description;
        this.recipe = recipe;
    }

    public Step() {
    }
}
