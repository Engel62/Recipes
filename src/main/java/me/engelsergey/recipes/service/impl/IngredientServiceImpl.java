package me.engelsergey.recipes.service.impl;

import me.engelsergey.recipes.model.Ingredient;
import me.engelsergey.recipes.service.IngredientService;
import org.apache.commons.lang3.ObjectUtils;
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
        ObjectUtils.isNotEmpty(ingredients);
        return ingredients.get(ingredientNumber);
    }

    @Override
    public Ingredient editIngredient(long ingredientNumber, Ingredient ingredient) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.put(ingredientNumber, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredientById(long ingredientNumber) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.remove(ingredientNumber);
            return true;
        }
        return false;
    }
}
