package com.acm.ioc;

/**
 * Inversión de Control (IoC)
 * Es un principio de diseño donde el control del flujo de un programa se invierte.
 * En lugar de que el código cliente controle la creación y gestión de objetos,
 * esta responsabilidad se delega a un contenedor o framework.
 * Ejemplo simple sin usar frameworks para ilustrar el concepto
 * Una fábrica que crea objetos en lugar del codigo cliente
 */
public class IoCMain {
    interface Transport{
        void move();
    }

    static class Bike implements Transport{
        @Override
        public void move() {
            System.out.println("Bike is moving");
        }
    }

    static class TransportFactory{
        public static Transport createTransport(String type){
            if(type.equalsIgnoreCase("bike")){
                return new Bike();
            }
            // Si gregamos mas tipos de transporte aqui, permitimos la extensión sin modificar el código existente
            throw new IllegalArgumentException("Unknown transport type");
        }
    }

    /**
     * Inversión de Control (IoC)
     * El codigo cliente (main) no controla la creacion, la fabrica tiene el control,
     * estamos "invirtiendo" el control de la creación de objetos
     * @param args
     */
    public static void main(String[] args) {
        Transport bike = TransportFactory.createTransport("bike");
        bike.move();
    }
}
