package com.JuanDavid.PricipioAbiertoCerrado.ManeraCorrecta;

public class DescuentoTemporadaBaja implements Descuento{
    @Override
    public void aplicarDescuento(double precio) {
        double descuento = precio * 0.20;
        double precioFinal = precio - descuento;
        System.out.println("Precio con descuento de temporada baja: " + precioFinal);
    }
}
