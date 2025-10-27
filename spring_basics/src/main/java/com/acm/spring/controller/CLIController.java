package com.acm.spring.controller;

import com.acm.spring.service.UserService;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;


@Controller
public class CLIController {


    private final UserService userService;

    public CLIController(UserService userService) {
        this.userService = userService;
    }

    public void getUserByName() {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Ingrese el nombre del usuario:");
        try{
            var userToFind = br.readLine();
            var users = userService.getAllUsers();
            var userFound = users.stream()
                    .filter(user -> user.equalsIgnoreCase(userToFind))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
