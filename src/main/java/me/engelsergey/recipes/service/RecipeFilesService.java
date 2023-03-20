package me.engelsergey.recipes.service;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
