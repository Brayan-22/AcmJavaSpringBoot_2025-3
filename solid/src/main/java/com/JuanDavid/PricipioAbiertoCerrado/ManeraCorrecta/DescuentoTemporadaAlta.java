package com.JuanDavid.PricipioAbiertoCerrado.ManeraCorrecta;

public class DescuentoTemporadaAlta implements Descuento{
    @Override
    public void aplicarDescuento(double precio) {
        double descuento = precio * 0.10;
        double precioFinal = precio - descuento;
        System.out.println("Precio con descuento de temporada alta: " + precioFinal);
    }
}
