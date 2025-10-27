package com.JuanDavid.PricipioAbiertoCerrado.ManeraCorrecta;

public class Main {

    public static void main(String[] args) {
        double precio = 100.0;

        Descuento descuentoBaja = new DescuentoTemporadaBaja();
        Descuento descuentoAlta = new DescuentoTemporadaAlta();
        Descuento descuentoNavidad = new DescuentoTemporadaNavidad();

        descuentoBaja.aplicarDescuento(precio);
        descuentoAlta.aplicarDescuento(precio);
        descuentoNavidad.aplicarDescuento(precio);

    }
}