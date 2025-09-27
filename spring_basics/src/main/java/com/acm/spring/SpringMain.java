package com.acm.spring;

import com.acm.spring.components.Ejemplo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.BeanDefinition;
import java.util.concurrent.ExecutionException;
/*
* Spring nacio como un framework para simplificar el desarrollo de aplicaciones Java empresariales,
* proporcionando una infraestructura ligera y modular que facilita la creación, configuración y gestión de componentes (objetos) en una aplicación
* Aplicando el principio de Inversión de Control (IoC) y la inyección de dependencias (DI)
* */

@Configuration
public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMain.class);

        EjemploService service1 = context.getBean(EjemploService.class);
        service1.ejecutar();
    }

    @Bean("miEjemplo1")
    @Scope("prototype") // Cada vez que se solicite el bean, se creará una nueva instancia
    public Ejemplo getEjemplo(){
        return new Ejemplo("Hola desde Spring Framework");
    }

    @Bean("miEjemplo2")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE) // Por defecto, Spring crea beans como singleton
    public Ejemplo getEjemplo2(){
        return new Ejemplo("Hola desde Spring Framework 2");
    }

    @Component
    static class EjemploService{
        private Ejemplo ejemplo;

        public EjemploService(@Qualifier("miEjemplo2") Ejemplo ejemplo) {
            this.ejemplo = ejemplo;
        }
        public void ejecutar(){
            ejemplo.saludar();
        }
    }

}
