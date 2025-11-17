package com.acm.base_datos.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "warehouse_product")
public class AlmacenProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long stock;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product_fk", nullable = false)
    private ProductoEntity producto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_warehouse_fk", nullable = false)
    private AlmacenEntity almacen;
}
