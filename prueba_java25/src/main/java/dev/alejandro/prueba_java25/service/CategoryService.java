package dev.alejandro.prueba_java25.service;

import dev.alejandro.prueba_java25.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryDTO> findById(Long id);
    List<CategoryDTO> findAll();
    CategoryDTO save(CategoryDTO dto);
    void deleteById(Long id);
    Optional<CategoryDTO> update(Long id, CategoryDTO dto);
}
