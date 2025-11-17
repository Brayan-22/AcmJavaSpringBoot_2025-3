package com.acm.base_datos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32, nullable = false)
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    @Column(length = 32, nullable = false, unique = true)
    private String email;
    @Column(name = "fecha_registro", nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaRegistro;
    private String telefono;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rol_id_fk",nullable = false)
    private RolUsuarioEntity rolUsuario;
}