package com.JuanDavid.ResponsablidadUnica.ManeraCorrecta;

public class Main {
    public static void main(String[] args) {
        Factura factura = new Factura(1, 1000.0);

        // Calcular impuesto
        double impuesto = factura.getPrecio() * 0.19;
        System.out.println("Impuesto: " + impuesto);

        // Guardar en base de datos
        FacturaRepository facturaRepository = new FacturaRepository();
        facturaRepository.guardarEnBaseDeDatos(factura);

        // Enviar notificacion via email
        FacturaEmailService facturaEmailService = new FacturaEmailService();
        facturaEmailService.enviarNotificacionEmail(factura);
    }
}
