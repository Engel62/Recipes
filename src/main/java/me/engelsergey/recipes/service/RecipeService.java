package me.engelsergey.recipes.service;

import me.engelsergey.recipes.model.Recipe;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);
}
