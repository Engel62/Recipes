package me.engelsergey.recipes.service;

import java.io.File;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();
}
