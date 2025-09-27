package com.JuanDavid.PrincipioSustitucionLiskov.ManeraCorrecta;

public class HabitacionNormal extends Habitacion{

    public HabitacionNormal(int numero) {
        super(numero);
    }

    @Override
    public boolean siPuedeReservar() {
        return true;
    }

    @Override
    public void reservar() {
        System.out.println("Habitacion normal numero " + numero + " reservada");
    }
}
