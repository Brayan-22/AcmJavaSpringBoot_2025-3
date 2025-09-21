package com.JuanDavid.PrincipioSegregacionInterfaces.ViolacionPrincipio;

public class ClienteHotel implements UsuarioHotel{
    @Override
    public void reservarHabitacion() {
        System.out.println("Reservando habitacion");
    }

    @Override
    public void solicitarServicioHabitacion() {
        System.out.println("Solicitando servicio a la habitacion");
    }

    @Override
    public void solicitarServicioSpa() {
        System.out.println("Solicitando servicio de spa");
    }

    @Override
    public void solicitarServicioRestaurante() {
        System.out.println("Solicitando servicio de restaurante");
    }

    @Override
    public void solicitarServicioGimnasio() {
        System.out.println("Solicitando servicio de gimnasio");
    }
}
