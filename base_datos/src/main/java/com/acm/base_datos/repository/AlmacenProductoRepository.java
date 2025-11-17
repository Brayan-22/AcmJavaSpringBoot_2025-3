package com.acm.base_datos.repository;

import com.acm.base_datos.entity.AlmacenProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlmacenProductoRepository extends JpaRepository<AlmacenProductoEntity,Long> {
    List<AlmacenProductoEntity> findByAlmacen_IdAndProducto_Id(Long almacenId, Long productoId);
}
