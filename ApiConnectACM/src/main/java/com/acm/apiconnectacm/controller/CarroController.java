package com.acm.apiconnectacm.controller;

import com.acm.apiconnectacm.model.Carro;
import com.acm.apiconnectacm.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public Mono<List<Carro>> getAllCarros() {
        return carroService.getAllCarros();
    }

}
