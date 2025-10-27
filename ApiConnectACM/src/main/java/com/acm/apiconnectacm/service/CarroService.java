package com.acm.apiconnectacm.service;

import com.acm.apiconnectacm.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CarroService {

    private final WebClient webClient;

    @Autowired
    public CarroService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<Carro>> getAllCarros() {
        return webClient.get()
                .uri("/carts")
                .retrieve()
                .bodyToFlux(Carro.class)
                .collectList();
    }


}
