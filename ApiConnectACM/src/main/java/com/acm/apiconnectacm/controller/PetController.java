package com.acm.apiconnectacm.controller;

import com.acm.apiconnectacm.model.Pet;
import com.acm.apiconnectacm.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/getPet/{petId}")
    public Mono<Pet> getPetById(@PathVariable Long petId) {
        return petService.getPetById(petId);
    }

    @PostMapping
    public Mono<Pet> createPet(Pet pet) {
        return petService.createPet(pet);
    }

}
