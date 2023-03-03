package me.engelsergey.recipes.service;

import me.engelsergey.recipes.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(long ingredientNumber);
}
