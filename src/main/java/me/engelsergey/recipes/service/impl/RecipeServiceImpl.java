package me.engelsergey.recipes.service.impl;

import me.engelsergey.recipes.model.Recipe;
import me.engelsergey.recipes.service.RecipeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private static long recipeNumber = 0;
@Override
    public Recipe addRecipe(Recipe recipe) {
    recipes.put(recipeNumber++, recipe);
    return recipe;
    }
@Override
    public Recipe getRecipe(long recipeNumber) {
    ObjectUtils.isNotEmpty(recipes);
        return recipes.get(recipeNumber);
    }

    @Override
    public Recipe editRecipe(long recipeNumber, Recipe recipe) {
        ObjectUtils.isNotEmpty(recipes);
        if (recipes.containsKey(recipeNumber)) {
            recipes.put(recipeNumber, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipeById(long recipeNumber) {
        ObjectUtils.isNotEmpty(recipes);
        if (recipes.containsKey(recipeNumber)) {
            recipes.remove(recipeNumber);
            return true;
        }
        return false;
    }

}
