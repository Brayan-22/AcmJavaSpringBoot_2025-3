package com.JuanDavid.PrincipioSegregacionInterfaces.ViolacionPrincipio;

public class EmpleadoHotel implements UsuarioHotel{
    @Override
    public void reservarHabitacion() {
        throw new UnsupportedOperationException("No es un cliente, no puede reservar habitacion");
    }

    @Override
    public void solicitarServicioHabitacion() {
        throw new UnsupportedOperationException("No es un cliente, no puede solicitar servicio a la habitacion");
    }

    @Override
    public void solicitarServicioSpa() {
        throw new UnsupportedOperationException("No es un cliente, no puede solicitar servicio de spa");
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
