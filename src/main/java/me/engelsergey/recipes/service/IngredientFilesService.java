package me.engelsergey.recipes.service;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
