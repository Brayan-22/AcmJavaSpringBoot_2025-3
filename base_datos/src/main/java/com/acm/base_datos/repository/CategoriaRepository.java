package com.acm.base_datos.repository;

import com.acm.base_datos.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Long> {
}
