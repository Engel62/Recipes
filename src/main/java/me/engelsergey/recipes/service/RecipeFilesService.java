package me.engelsergey.recipes.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();
    Path createTempFile(String suffix);

    boolean cleanDataFile();

    boolean uploadDataFile(MultipartFile file);
}
