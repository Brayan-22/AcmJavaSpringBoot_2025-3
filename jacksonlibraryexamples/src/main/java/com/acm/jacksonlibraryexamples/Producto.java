package com.acm.jacksonlibraryexamples;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from serialization
public class Producto {
    @JsonProperty("producto_id")
    private Long id;
    @JsonProperty("producto_nombre")
    private String nombre;
    private Double precio;
    @JsonIgnore
    private String descripcion;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonAlias({"categoria_producto", "tipo_categoria"}) // Accept multiple names during deserialization
    private String categoria;

}
