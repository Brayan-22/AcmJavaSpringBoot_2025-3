package com.acm.base_datos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "warehouse")
public class AlmacenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

//    @OneToMany(mappedBy = "almacen",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<AlmacenProductoEntity> productosAlmacenados = new ArrayList<>();
}
