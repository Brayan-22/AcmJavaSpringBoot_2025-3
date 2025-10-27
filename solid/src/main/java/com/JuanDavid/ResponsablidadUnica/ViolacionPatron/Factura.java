package com.JuanDavid.ResponsablidadUnica.ViolacionPatron;

public class Factura {

    private int id;

    private double precio;

    public Factura(int id, double precio) {
        this.id = id;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Metodo para calcular impuesto
    public double calcularImpuesto() {
        return this.precio * 0.19;
    }

    //Metodo para guardar en base de datos
    public void guardarEnBaseDeDatos() {
        System.out.println("Guardando factura en base de datos...");
    }

    //Metodo para enviar notificacion via email
    public void enviarNotificacionEmail() {
        System.out.println("Enviando notificacion de factura via email...");
    }

}
