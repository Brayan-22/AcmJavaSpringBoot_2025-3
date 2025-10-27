package dev.alejandro.prueba_java25.service.impl;

import dev.alejandro.prueba_java25.dto.CategoryDTO;
import dev.alejandro.prueba_java25.persistence.entity.CategoryEntity;
import dev.alejandro.prueba_java25.persistence.repository.CategoryJpaRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
class CategoryServiceImplTest {
    @Mock
    private CategoryJpaRepository jpaRepository;
    @InjectMocks
    private CategoryServiceImpl service;

    @Test
    void findById_whenCategoryExists_shouldReturnCategoryDTO() {
        // Given
        Long id = 1L;
        CategoryEntity entity = CategoryEntity.builder()
                .id(id)
                .name("Electronics")
                .description("Electronic products")
                .build();
        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

        // When
        Optional<CategoryDTO> result = service.findById(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getName());
        assertEquals("Electronic products", result.get().getDescription());
        verify(jpaRepository, times(1)).findById(id);
    }

    @Test
    void findById_whenCategoryNotExists_shouldReturnEmpty() {
        // Given
        Long id = 999L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<CategoryDTO> result = service.findById(id);

        // Then
        assertTrue(result.isEmpty());
        verify(jpaRepository, times(1)).findById(id);
    }

    @Test
    void findAll_shouldReturnListOfCategoryDTOs() {
        // Given
        List<CategoryEntity> entities = Arrays.asList(
                CategoryEntity.builder().id(1L).name("Electronics").description("Electronic products").build(),
                CategoryEntity.builder().id(2L).name("Books").description("All kinds of books").build()
        );
        when(jpaRepository.findAll()).thenReturn(entities);

        // When
        List<CategoryDTO> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Books", result.get(1).getName());
        verify(jpaRepository, times(1)).findAll();
    }

    @Test
    void save_shouldSaveCategoryAndReturnDTO() {
        // Given
        CategoryDTO dto = CategoryDTO.builder()
                .name("Sports")
                .description("Sports equipment")
                .build();
        CategoryEntity savedEntity = CategoryEntity.builder()
                .id(1L)
                .name("Sports")
                .description("Sports equipment")
                .build();
        when(jpaRepository.save(any(CategoryEntity.class))).thenReturn(savedEntity);

        // When
        CategoryDTO result = service.save(dto);

        // Then
        assertNotNull(result);
        assertEquals("Sports", result.getName());
        assertEquals("Sports equipment", result.getDescription());
        verify(jpaRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        // Given
        Long id = 1L;
        doNothing().when(jpaRepository).deleteById(id);

        // When
        service.deleteById(id);

        // Then
        verify(jpaRepository, times(1)).deleteById(id);
    }

    @Test
    void update_whenCategoryExists_shouldUpdateAndReturnDTO() {
        // Given
        Long id = 1L;
        CategoryDTO dto = CategoryDTO.builder()
                .name("Updated Electronics")
                .description("Updated description")
                .build();
        CategoryEntity existingEntity = CategoryEntity.builder()
                .id(id)
                .name("Electronics")
                .description("Old description")
                .build();
        CategoryEntity updatedEntity = CategoryEntity.builder()
                .id(id)
                .name("Updated Electronics")
                .description("Updated description")
                .build();
        when(jpaRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(jpaRepository.save(any(CategoryEntity.class))).thenReturn(updatedEntity);

        // When
        Optional<CategoryDTO> result = service.update(id, dto);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Updated Electronics", result.get().getName());
        assertEquals("Updated description", result.get().getDescription());
        verify(jpaRepository, times(1)).findById(id);
        verify(jpaRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    void update_whenCategoryNotExists_shouldReturnEmpty() {
        // Given
        Long id = 999L;
        CategoryDTO dto = CategoryDTO.builder()
                .name("NonExistent")
                .description("Description")
                .build();
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<CategoryDTO> result = service.update(id, dto);

        // Then
        assertTrue(result.isEmpty());
        verify(jpaRepository, times(1)).findById(id);
        verify(jpaRepository, never()).save(any(CategoryEntity.class));
    }
}