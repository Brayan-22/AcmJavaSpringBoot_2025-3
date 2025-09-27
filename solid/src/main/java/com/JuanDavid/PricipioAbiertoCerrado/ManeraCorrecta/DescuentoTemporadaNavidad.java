package com.JuanDavid.PricipioAbiertoCerrado.ManeraCorrecta;

public class DescuentoTemporadaNavidad implements Descuento{
    @Override
    public void aplicarDescuento(double precio) {
        double descuento = precio * 0.25;
        double precioFinal = precio - descuento;
        System.out.println("Precio con descuento de temporada de navidad: " + precioFinal);
    }
}
