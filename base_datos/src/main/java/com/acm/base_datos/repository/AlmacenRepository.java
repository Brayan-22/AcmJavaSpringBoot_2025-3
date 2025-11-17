package com.acm.base_datos.repository;

import com.acm.base_datos.entity.AlmacenEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlmacenRepository extends JpaRepository<AlmacenEntity,Long> {
}
