package dev.alejandro.springboot_firstclass.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ClaseA {
    private final ClaseB claseB;

    public ClaseA(ClaseB claseB) {
        this.claseB = claseB;
    }
    public void mensaje() {
        System.out.println("Mensaje desde ClaseA");
    }
}
