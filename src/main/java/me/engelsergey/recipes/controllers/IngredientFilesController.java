package me.engelsergey.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.engelsergey.recipes.model.Ingredient;
import me.engelsergey.recipes.service.IngredientFilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/ingredient/files")
@Tag(name = "Ingredients files controller", description = "Operations with ingredients file")
public class IngredientFilesController {

    private final IngredientFilesService ingredientFilesService;

    public IngredientFilesController(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "Downloading ingredients file",
            description = " Download ingredients file"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ingredients file was successfully downloaded",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Ingredients file has no content",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = ingredientFilesService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientsLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Uploading new ingredients file",
            description = "Upload new ingredients file"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "New ingredients file was successfully uploaded"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<Void> uploadDataFIle(@RequestParam MultipartFile file) {
        ingredientFilesService.uploadDataFile(file);
        if (file.getResource().exists()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

