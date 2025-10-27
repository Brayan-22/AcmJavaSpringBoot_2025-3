package dev.alejandro.prueba_java25.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alejandro.prueba_java25.dto.CategoryDTO;
import dev.alejandro.prueba_java25.persistence.entity.CategoryEntity;
import dev.alejandro.prueba_java25.persistence.repository.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@Tag("integration")
class CategoryControllerIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CategoryJpaRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/prueba/api/v1/category/";
        repository.deleteAll();
    }

    @Test
    void shouldCreateCategory() throws Exception {
        // Given
        CategoryDTO dto = CategoryDTO.builder()
                .name("Electronics")
                .description("Electronic products")
                .build();

        // When
        ResponseEntity<Map> response = restTemplate.postForEntity(
                baseUrl,
                dto,
                Map.class
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().get("status"));
        assertEquals("Category saved", response.getBody().get("message"));
        Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
        assertEquals("Electronics", data.get("name"));
        assertEquals("Electronic products", data.get("description"));
    }

    @Test
    void shouldGetAllCategories() {
        // Given
        repository.save(CategoryEntity.builder().name("Electronics").description("Electronic products").build());
        repository.save(CategoryEntity.builder().name("Books").description("All kinds of books").build());

        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
                baseUrl,
                Map.class
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().get("status"));
        assertEquals("Categories found", response.getBody().get("message"));
        assertEquals(2, response.getBody().get("totalItems"));
    }

    @Test
    void shouldGetCategoryById() {
        // Given
        CategoryEntity saved = repository.save(
                CategoryEntity.builder()
                        .name("Electronics")
                        .description("Electronic products")
                        .build()
        );

        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
                baseUrl + saved.getId(),
                Map.class
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().get("status"));
        assertEquals("Category found", response.getBody().get("message"));
        Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
        assertEquals("Electronics", data.get("name"));
        assertEquals("Electronic products", data.get("description"));
    }

    @Test
    void shouldUpdateCategory() {
        // Given
        CategoryEntity saved = repository.save(
                CategoryEntity.builder()
                        .name("Electronics")
                        .description("Electronic products")
                        .build()
        );
        CategoryDTO updateDto = CategoryDTO.builder()
                .name("Updated Electronics")
                .description("Updated description")
                .build();

        // When
        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl + saved.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(updateDto),
                Map.class
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("success", response.getBody().get("status"));
        assertEquals("Category updated", response.getBody().get("message"));
        Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
        assertEquals("Updated Electronics", data.get("name"));
        assertEquals("Updated description", data.get("description"));
    }

    @Test
    void shouldReturnErrorWhenCategoryNotFound() {
        // When
        ResponseEntity<Map> response = restTemplate.getForEntity(
                baseUrl + 999L,
                Map.class
        );

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("Category not found", response.getBody().get("message"));
    }
}
