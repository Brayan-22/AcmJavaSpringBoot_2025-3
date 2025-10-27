package dev.alejandro.prueba_java25.service.impl;

import dev.alejandro.prueba_java25.dto.CategoryDTO;
import dev.alejandro.prueba_java25.persistence.entity.CategoryEntity;
import dev.alejandro.prueba_java25.persistence.repository.CategoryJpaRepository;
import dev.alejandro.prueba_java25.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryJpaRepository jpaRepository;
    @Override
    public Optional<CategoryDTO> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity ->
                        CategoryDTO.builder()
                                .name(entity.getName())
                                .description(entity.getDescription())
                                .build())
                .or(Optional::empty);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity ->
                        CategoryDTO.builder()
                                .name(entity.getName())
                                .description(entity.getDescription())
                                .build())
                .toList();
    }

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        var saved = jpaRepository.save(CategoryEntity.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                .build());
        return CategoryDTO.builder()
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryDTO> update(Long id, CategoryDTO dto) {
        var updated = jpaRepository.findById(id)
                .map(entity -> {
                    entity.setName(dto.getName());
                    entity.setDescription(dto.getDescription());
                    return jpaRepository.save(entity);
                })
                .or(Optional::empty);
        return updated.isEmpty()? Optional.empty():
                updated.map(entity -> CategoryDTO.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build());
    }
}
