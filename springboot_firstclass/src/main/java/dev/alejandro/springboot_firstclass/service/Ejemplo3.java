package dev.alejandro.springboot_firstclass.service;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


public class Ejemplo3 implements EjemploInterface{
    @Override
    public void mensaje() {
        System.out.println("Mensaje desde Ejemplo3");
    }
}
