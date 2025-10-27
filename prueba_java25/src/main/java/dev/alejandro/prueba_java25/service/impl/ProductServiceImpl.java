package dev.alejandro.prueba_java25.service.impl;

import dev.alejandro.prueba_java25.dto.ProductDTO;
import dev.alejandro.prueba_java25.persistence.entity.ProductEntity;
import dev.alejandro.prueba_java25.persistence.repository.ProductJpaRepository;
import dev.alejandro.prueba_java25.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository jpaRepository;

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity ->
                        ProductDTO.builder()
                                .name(entity.getName())
                                .price(entity.getPrice())
                                .description(entity.getDescription())
                                .build())
                .or(Optional::empty);
    }

    @Override
    public List<ProductDTO> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity ->
                        ProductDTO.builder()
                                .name(entity.getName())
                                .price(entity.getPrice())
                                .description(entity.getDescription())
                                .build())
                .toList();
    }

    @Override
    public ProductDTO save(ProductDTO dto) {
        var saved = jpaRepository.save(ProductEntity.builder()
                        .name(dto.getName())
                        .price(dto.getPrice())
                        .description(dto.getDescription())
                .build());
        return ProductDTO.builder()
                .name(saved.getName())
                .price(saved.getPrice())
                .description(saved.getDescription())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDTO> update(Long id, ProductDTO dto) {
        var updated = jpaRepository.findById(id)
                .map(entity -> {
                    entity.setName(dto.getName());
                    entity.setPrice(dto.getPrice());
                    entity.setDescription(dto.getDescription());
                    return jpaRepository.save(entity);
                }).or(Optional::empty);

        return updated.isEmpty() ? Optional.empty():
                updated.map(entity ->ProductDTO.builder()
                        .name(entity.getName())
                        .price(entity.getPrice())
                        .description(entity.getDescription())
                        .build());
    }
}
