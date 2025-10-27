package com.acm.apiconnectacm.service;

import com.acm.apiconnectacm.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PetService {

    private final WebClient webClientPetstore;

    @Autowired
    public PetService(WebClient webClientPetstore) {
        this.webClientPetstore = webClientPetstore;
    }

    public Mono<Pet> getPetById(Long petId) {
        return webClientPetstore.get()
                .uri("/pet/{petId}", petId)
                .retrieve()
                .bodyToMono(Pet.class);
    }

    public Mono<Pet> createPet(Pet pet) {
        return webClientPetstore.post()
                .uri("/pet")
                .bodyValue(pet)
                .retrieve()
                .bodyToMono(Pet.class);
    }

}
