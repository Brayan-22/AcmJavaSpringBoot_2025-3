package dev.alejandro.springboot_firstclass.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("experimentService1")
@Data
@Lazy
public class ExperimentService {
    String mensaje;
    public ExperimentService(@Value("#{ 'Experiment Service1' }") String mensaje) {
        this.mensaje = mensaje;
        System.out.println("Experiment Service1: " + mensaje);
    }
}
