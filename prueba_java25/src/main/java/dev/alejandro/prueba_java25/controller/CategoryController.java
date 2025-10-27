package dev.alejandro.prueba_java25.controller;

import dev.alejandro.prueba_java25.dto.CategoryDTO;
import dev.alejandro.prueba_java25.service.CategoryService;
import dev.alejandro.prueba_java25.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category/")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        var categories = categoryService.findAll();
        return ResponseHandler.generateListSuccessResponse("Categories found", categories, categories.size(), categories.isEmpty() ? 0 : 1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        var category = categoryService.findById(id);
        return category.map(categoryDTO -> ResponseHandler.generateSuccessResponse("Category found", categoryDTO))
                .orElseGet(() -> ResponseHandler.generateErrorResponse("Category not found", null));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CategoryDTO dto){
        var saved = categoryService.save(dto);
        return ResponseHandler.generateSuccessResponse("Category saved", saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        var updated = categoryService.update(id, dto);
        return updated.map(categoryDTO -> ResponseHandler.generateSuccessResponse("Category updated", categoryDTO))
                .orElseGet(() -> ResponseHandler.generateErrorResponse("Category not found", null));
    }
}
