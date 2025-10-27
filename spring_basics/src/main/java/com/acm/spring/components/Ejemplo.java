package com.acm.spring.components;

public class Ejemplo {
    private String saludo;
    public Ejemplo(String saludo){
        this.saludo = saludo;
    }
    public void saludar(){
        System.out.println(this.saludo);
    }
}
