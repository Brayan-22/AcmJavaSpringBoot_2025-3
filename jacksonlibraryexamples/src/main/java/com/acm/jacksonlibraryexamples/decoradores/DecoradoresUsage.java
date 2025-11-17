package com.acm.jacksonlibraryexamples.decoradores;

import com.acm.jacksonlibraryexamples.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DecoradoresUsage {
    public void main(){
        Producto producto = new Producto();
        producto.setNombre("Producto");
        producto.setId(1L);
        producto.setPrecio(200.0);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(producto);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
