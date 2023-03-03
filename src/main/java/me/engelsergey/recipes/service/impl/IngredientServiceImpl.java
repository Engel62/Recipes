package me.engelsergey.recipes.service.impl;

import me.engelsergey.recipes.model.Ingredient;
import me.engelsergey.recipes.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long ingredientNumber = 0;


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(ingredientNumber++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {
        return ingredients.get(ingredientNumber);
    }
}
