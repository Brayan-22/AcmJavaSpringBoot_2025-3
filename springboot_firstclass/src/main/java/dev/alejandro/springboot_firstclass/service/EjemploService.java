package dev.alejandro.springboot_firstclass.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class EjemploService {

    @Lookup
    public Ejemplo lookupEjemplo() {
        return null; // Spring will override this method to return a new Ejemplo instance
    }

    public void usageEjemplo() {
        Ejemplo ejemplo = lookupEjemplo();
        System.out.println("Ejemplo: " + ejemplo);
        ejemplo.mensaje();

    }
}
