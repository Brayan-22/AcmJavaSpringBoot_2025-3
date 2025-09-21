package com.JuanDavid.PrincipioSustitucionLiskov.ViolacionPrincipio;

public class HabitacionSuite extends Habitacion{

    public HabitacionSuite(int numero) {
        super(numero);
    }

    @Override
    public void reservar() {
        throw new UnsupportedOperationException("No se puede reservar una habitacion suite asi");
    }
}
