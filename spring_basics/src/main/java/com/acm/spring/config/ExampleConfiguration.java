package com.acm.spring.config;

import com.acm.spring.components.Ejemplo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ExampleConfiguration {
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
}
