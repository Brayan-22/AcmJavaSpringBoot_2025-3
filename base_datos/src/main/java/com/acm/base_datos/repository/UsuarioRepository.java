package com.acm.base_datos.repository;

import com.acm.base_datos.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByNombreLikeIgnoreCase(String nombre);

    @NativeQuery(value = "SELECT u.* FROM users u inner join rol_user ru on u.rol_id_fk = ru.id where ru.id = :rolId")
    List<UsuarioEntity> findUserByRoleID(Long rolId);

    @Query(value = "SELECT u FROM UsuarioEntity u WHERE u.rolUsuario.id = :rolId")
    @Modifying
    List<UsuarioEntity> findUserByRoleIdFk(Long rolId);
}
