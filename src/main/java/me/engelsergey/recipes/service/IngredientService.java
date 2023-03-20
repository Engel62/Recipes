package me.engelsergey.recipes.service;

import me.engelsergey.recipes.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Map<Long, Ingredient> getAllIngredients();

    Ingredient getIngredient(long ingredientNumber);

    Ingredient editIngredient(long ingredientNumber, Ingredient ingredient);

    boolean deleteIngredientById(long ingredientNumber);

    void deleteAllIngredients();
}
