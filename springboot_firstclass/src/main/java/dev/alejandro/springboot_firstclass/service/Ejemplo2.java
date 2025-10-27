package dev.alejandro.springboot_firstclass.service;


public class Ejemplo2 implements  EjemploInterface {
    private final Integer id;
    public Ejemplo2(Integer id) {
        this.id = id;
    }
    @Override
    public void mensaje() {
        System.out.println("Mensaje desde Ejemplo2 con id: " + id);
    }
}
