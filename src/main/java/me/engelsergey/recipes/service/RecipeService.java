package me.engelsergey.recipes.service;

import me.engelsergey.recipes.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);

    Recipe editRecipe(long recipeNumber, Recipe recipe);

    boolean deleteRecipeById(long recipeNumber);
    Path createTextDataFile() throws IOException;

}
