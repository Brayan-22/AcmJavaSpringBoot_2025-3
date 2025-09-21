package com.JuanDavid.PrincipioSustitucionLiskov.ManeraCorrecta;

public class HabitacionPresidencial extends Habitacion{
    public HabitacionPresidencial(int numero) {
        super(numero);
    }

    @Override
    public boolean siPuedeReservar() {
        return false;
    }

    @Override
    public void reservar() {
        System.out.println("No se puede reservar una habitacion presidencial asi");
    }
}
