package dev.alejandro.springboot_firstclass.config;

import dev.alejandro.springboot_firstclass.service.Ejemplo;
import dev.alejandro.springboot_firstclass.service.Ejemplo2;
import dev.alejandro.springboot_firstclass.service.EjemploInterface;
import dev.alejandro.springboot_firstclass.service.ExperimentService;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration
public class EjemploConfiguration {

    @Bean
    @Scope("prototype")
    public Ejemplo ejemplo(){
        return new Ejemplo("Mensaje desde la configuracion");
    }

    @Bean
    @Order(1)
    public EjemploInterface ejemplo2(){
        return new Ejemplo2(1);
    }

    @Bean
    @Order(2)
    public EjemploInterface ejemplo22(){
        return new Ejemplo2(2);
    }

    @Bean("experimentService2")

    public ExperimentService experimentService(){
        return new ExperimentService("Experiment Service2" );
    }
}
