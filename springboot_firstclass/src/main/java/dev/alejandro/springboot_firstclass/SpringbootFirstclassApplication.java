package dev.alejandro.springboot_firstclass;

import dev.alejandro.springboot_firstclass.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootFirstclassApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootFirstclassApplication.class, args);
    }
    @Bean
    ApplicationRunner runner(ClaseA claseA){
        return args ->{
            claseA.mensaje();
        };
    }
}