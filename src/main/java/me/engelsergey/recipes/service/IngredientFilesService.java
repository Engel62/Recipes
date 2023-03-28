package me.engelsergey.recipes.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();

    boolean uploadDataFile(MultipartFile file);
}
