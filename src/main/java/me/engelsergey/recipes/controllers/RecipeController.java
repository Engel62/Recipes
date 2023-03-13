package me.engelsergey.recipes.controllers;

import me.engelsergey.recipes.model.Recipe;
import me.engelsergey.recipes.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService =recipeService;
    }
@PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
    Recipe createRecip = recipeService.addRecipe(recipe);
    return ResponseEntity.ok(createRecip);
}
    @PostMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber){
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (recipe == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long recipeNumber, @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.editRecipe(recipeNumber, recipe);
        if (recipe1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }

    @DeleteMapping("/{recipeNumber}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable long recipeNumber) {
        if (recipeService.deleteRecipeById(recipeNumber)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
