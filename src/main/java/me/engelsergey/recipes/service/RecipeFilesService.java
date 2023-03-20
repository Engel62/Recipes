package me.engelsergey.recipes.service;

import java.io.File;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();
}
