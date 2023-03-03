package me.engelsergey.recipes.controllers;

import me.engelsergey.recipes.model.Ingredient;
import me.engelsergey.recipes.service.IngredientService;
import me.engelsergey.recipes.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
@PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createIngredient);
    }

    @GetMapping("/{ingredientNumber}")
    public ResponseEntity<Ingredient> getIngredietn(@PathVariable long ingredientNumber) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientNumber);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
