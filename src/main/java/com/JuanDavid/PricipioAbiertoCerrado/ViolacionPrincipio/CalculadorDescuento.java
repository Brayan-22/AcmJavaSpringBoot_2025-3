package com.JuanDavid.PricipioAbiertoCerrado.ViolacionPrincipio;

public class CalculadorDescuento {
    public double calcularDescuento(String tipoCliente, double precio) {
        if (tipoCliente.equals("TEMPORADA_BAJA")) {
            return precio * 0.1; // 10% de descuento para clientes regulares
        } else if (tipoCliente.equals("TEMPORADA_ALTA")) {
            return precio * 0.2; // 20% de descuento para clientes premium
        } else if (tipoCliente.equals("TEMPORADA_MITAD_AÑO")){
            return precio * 0.25; // 25% de descuento para clientes navideños
        } else {
            return 0; // Sin descuento para otros tipos de clientes
        }
    }
}
