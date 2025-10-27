package dev.alejandro.springboot_firstclass.service;

import org.springframework.stereotype.Component;

@Component
public class ClaseB {
    private final ClaseA claseA;
    public ClaseB(ClaseA claseA) {
        this.claseA = claseA;
    }

    public void mensaje() {
        System.out.println("Mensaje desde ClaseB");
    }
}
