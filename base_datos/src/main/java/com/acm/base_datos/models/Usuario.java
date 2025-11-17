package com.acm.base_datos.models;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String email;
    private LocalDateTime fechaRegistro;
    private String telefono;
}
