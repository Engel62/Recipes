package me.engelsergey.recipes.service.impl;

import me.engelsergey.recipes.service.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {
    @Value("${path.to.files.folder}")
    private String ingredientsFilePath;

    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientsFilePath, ingredientsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public File getDataFile() {
        return new File(ingredientsFilePath, ingredientsFileName);
    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientsFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
