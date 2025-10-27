package dev.alejandro.springboot_firstclass.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Ejemplo {
    private String mensaje;

    public Ejemplo(String mensaje) {
        this.mensaje = mensaje;
    }
    public void mensaje() {
        System.out.println(mensaje);
    }
}
