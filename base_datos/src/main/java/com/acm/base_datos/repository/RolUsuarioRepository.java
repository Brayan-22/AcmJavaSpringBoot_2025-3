package com.acm.base_datos.repository;

import com.acm.base_datos.entity.RolUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolUsuarioRepository extends JpaRepository<RolUsuarioEntity,Long> {

}
