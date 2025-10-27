package com.acm.dep_inversion;


/**
 * * Principio de Inversión de Dependencias (DIP)
 *  Los modulos de alto nivel (Como la logica de negocio)
 *  no deben depender de los módulos de bajo nivel (Detalles de implementación)
 *  Ambos deben depender de abstracciones (Interfaces)
 */
public class MainDIP {
    // Abstracción
    interface Device{
        void start();
    }
    // Implementación concreta
    static class TV implements Device{
        @Override
        public void start() {
            System.out.println("TV started");
        }
    }
    // Modulo de alto nivel
    static class RemoteControl {
        private Device device;

        public RemoteControl(Device device) {
            // La clase ya no crea la instancia del dispositivo
            // Ahora recibe una abstracción (interfaz) a través del constructor
            this.device = device; // Inyección de dependencia
        }

        public void turnOn() {
            device.start();
            System.out.println("Remote control: Device is turned on");
        }
    }
    public static void main(String[] args) {
        Device tv = new TV(); // Crear la implementación concreta
        RemoteControl remote = new RemoteControl(tv); // Inyectar la dependencia
        remote.turnOn();
    }
}
