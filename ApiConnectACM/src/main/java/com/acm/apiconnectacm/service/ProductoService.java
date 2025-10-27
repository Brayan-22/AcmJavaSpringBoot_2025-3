package com.acm.apiconnectacm.service;

import com.acm.apiconnectacm.model.Carro;
import com.acm.apiconnectacm.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductoService {

    private final WebClient webClient;

    @Autowired
    public ProductoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<Producto>> getAllProductos() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Producto.class)
                .collectList();
    }

    public Mono<Producto> getProductoById(int id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class);
    }

    public Mono<Producto> createProducto(Producto producto) {
        return webClient.post()
                .uri("/products")
                .bodyValue(producto)
                .retrieve()
                .bodyToMono(Producto.class);
    }

    public Mono<Producto> deleteProducto(int id) {
        return webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class);
    }

    public Mono<Producto> updateProducto(int id, Producto producto) {
        return webClient.put()
                .uri("/products/{id}", id)
                .bodyValue(producto)
                .retrieve()
                .bodyToMono(Producto.class);
    }

}
