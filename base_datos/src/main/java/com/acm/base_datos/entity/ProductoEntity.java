package com.acm.base_datos.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String nombre;
    @Column(name = "price", nullable = false)
    private Double precio;
    @Column(name = "description")
    private String descripcion;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
}
