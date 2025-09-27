package com.JuanDavid.PrincipioSegregacionInterfaces.ManeraCorrecta;

public class ClienteHotel implements ReservarHabitacion{
    @Override
    public void reservarHabitacion() {
        System.out.println("Cliente reservando una habitación.");
    }
}
