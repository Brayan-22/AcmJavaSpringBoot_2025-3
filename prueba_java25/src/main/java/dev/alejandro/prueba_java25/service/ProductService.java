package dev.alejandro.prueba_java25.service;

import dev.alejandro.prueba_java25.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDTO> findById(Long id);
    List<ProductDTO> findAll();
    ProductDTO save(ProductDTO dto);
    void deleteById(Long id);
    Optional<ProductDTO> update(Long id, ProductDTO dto);
}