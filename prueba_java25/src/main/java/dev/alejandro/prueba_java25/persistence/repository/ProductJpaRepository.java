package dev.alejandro.prueba_java25.persistence.repository;

import dev.alejandro.prueba_java25.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
