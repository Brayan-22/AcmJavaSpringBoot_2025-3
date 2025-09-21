package com.JuanDavid.PrincipioSustitucionLiskov.ManeraCorrecta;

public abstract class Habitacion {

    protected int numero;

    public Habitacion(int numero) {
        this.numero = numero;
    }

    public abstract boolean siPuedeReservar();
    public abstract void reservar();

}
