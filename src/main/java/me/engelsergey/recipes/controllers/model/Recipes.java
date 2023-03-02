package me.engelsergey.recipes.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
@Data
@AllArgsConstructor
public class   Recipes {
    private String name;
    private int cookingTime;
    private  ArrayList<Ingredient>ingredients;
    private ArrayList<String> cookingSteps;

}
