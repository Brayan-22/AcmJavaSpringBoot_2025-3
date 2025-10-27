package dev.alejandro.prueba_java25.controller;

import dev.alejandro.prueba_java25.dto.ProductDTO;
import dev.alejandro.prueba_java25.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return  ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.save(productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return  ResponseEntity.ok(productService.update(id, productDTO));
    }
}
