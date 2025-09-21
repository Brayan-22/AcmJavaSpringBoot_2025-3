package com.JuanDavid.PrincipioSustitucionLiskov.ViolacionPrincipio;

public class Habitacion {

    protected int numero;

    protected boolean disponible = true;

    public Habitacion(int numero) {
        this.numero = numero;
    }

    public void reservar(){
        if(disponible){
            disponible = false;
            System.out.println("Habitacion "+numero+" reservada");
        } else {
            System.out.println("Habitacion "+numero+" no disponible");
        }
    }

    public boolean isDisponible() {
        return disponible;
    }
}
