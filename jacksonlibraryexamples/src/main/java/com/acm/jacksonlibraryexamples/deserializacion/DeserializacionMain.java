package com.acm.jacksonlibraryexamples.deserializacion;


import com.acm.jacksonlibraryexamples.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class DeserializacionMain {
    public void main(){
        String json = """
                {
                    "id": 1,
                    "nombre": "Juan Perez",
                    "email": "juan@correo.com"
                }
                """;
        Usuario usuario = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            usuario = objectMapper.readValue(json, Usuario.class);
            System.out.println(usuario);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
