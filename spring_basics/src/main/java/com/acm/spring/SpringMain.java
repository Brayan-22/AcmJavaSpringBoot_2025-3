package com.acm.spring;

import com.acm.spring.controller.CLIController;
import com.acm.spring.service.EjemploService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
/*
* Spring nacio como un framework para simplificar el desarrollo de aplicaciones Java empresariales,
* proporcionando una infraestructura ligera y modular que facilita la creación, configuración y gestión de componentes (objetos) en una aplicación
* Aplicando el principio de Inversión de Control (IoC) y la inyección de dependencias (DI)
* */


@ComponentScan
public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMain.class);
        var cliController = context.getBean(CLIController.class);
        cliController.getUserByName();
    }
}
