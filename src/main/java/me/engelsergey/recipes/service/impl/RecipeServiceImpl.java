package me.engelsergey.recipes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.engelsergey.recipes.model.Recipe;
import me.engelsergey.recipes.service.RecipeFilesService;
import me.engelsergey.recipes.service.RecipeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceImpl implements RecipeService {
    public final RecipeFilesService recipeFilesService;
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private static long recipeNumber = 0;

    public RecipeServiceImpl(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }
    @PostConstruct
    private void init(){
        File file = recipeFilesService.getDataFile();
        if (file.exists()) {
            recipeFilesService.readFromFile();
        }
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
    recipes.put(recipeNumber++, recipe);
    saveToFile();

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
            saveToFile();
            return true;
        }
        return false;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = recipeFilesService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
