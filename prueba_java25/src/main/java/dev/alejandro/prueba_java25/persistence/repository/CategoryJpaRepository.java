package dev.alejandro.prueba_java25.persistence.repository;

import dev.alejandro.prueba_java25.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
