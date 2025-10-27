package com.acm.apiconnectacm.controller;

import com.acm.apiconnectacm.model.Producto;
import com.acm.apiconnectacm.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Mono<List<Producto>> getProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/getProducto/{id}")
    public Mono<Producto> getProductoPorId(@PathVariable int id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public Mono<Producto> createProducto(Producto producto){
        return productoService.createProducto(producto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Producto> deleteProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @PutMapping("/update/{id}")
    public Mono<Producto> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto);
    }

}
