package com.acm.spring.service;

import com.acm.spring.components.Ejemplo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EjemploService {
    private Ejemplo ejemplo;

    public EjemploService(@Qualifier("miEjemplo2") Ejemplo ejemplo) {
        this.ejemplo = ejemplo;
    }
    public void ejecutar(){
        ejemplo.saludar();
    }
}
