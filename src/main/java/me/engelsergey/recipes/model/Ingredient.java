package me.engelsergey.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private int numberOfIngredients;
    private String unitOfMeasurement;
}
