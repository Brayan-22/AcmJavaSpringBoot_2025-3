package com.JuanDavid.PrincipioSustitucionLiskov.ViolacionPrincipio;

public class HabitacionNormal extends Habitacion{

    public HabitacionNormal(int numero) {
        super(numero);
    }

    @Override
    public void reservar() {
        super.reservar();
    }
}
