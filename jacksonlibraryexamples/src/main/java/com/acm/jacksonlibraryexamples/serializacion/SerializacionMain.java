package com.acm.jacksonlibraryexamples.serializacion;

import com.acm.jacksonlibraryexamples.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class SerializacionMain {

    public void main(){
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Juan Perez")
                .email("juan@correo.com")
                .build();
        try{
            String jsonString = mapper.writeValueAsString(usuario);
            System.out.println(jsonString);

            String jsonPretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usuario);
            System.out.println(jsonPretty);

            // Guardar en un archivo
            mapper.writeValue(new File("usuario.json"), usuario);

            // Guardar en un outputStream
            mapper.writeValue(new FileOutputStream("usuario.json"), usuario);

            // Pretty mapper
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("usuario.json"), usuario);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
