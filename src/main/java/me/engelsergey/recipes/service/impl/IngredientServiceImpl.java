package me.engelsergey.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.engelsergey.recipes.model.Ingredient;
import me.engelsergey.recipes.service.IngredientFilesService;
import me.engelsergey.recipes.service.IngredientService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientFilesService ingredientFilesService;
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long ingredientNumber = 0;

    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }


  @PostConstruct
  private void init() {
       readFromFile();
   }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(ingredientNumber++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Map<Long, Ingredient> getAllIngredients() {
        ObjectUtils.isNotEmpty(ingredients);
        return ingredients;
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
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredientById(long ingredientNumber) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.remove(ingredientNumber);
            saveToFile();
            return true;
        }
        return false;
    }


    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = ingredientFilesService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
