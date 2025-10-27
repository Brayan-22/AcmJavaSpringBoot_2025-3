package com.acm.spring.service.impl;

import com.acm.spring.persistence.UserRepository;
import com.acm.spring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // Inyección por atributo (no recomendado)
    private final UserRepository userRepository;

    // Inyección por constructor (recomendado)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<String> getAllUsers() {
        if (userRepository == null) {
            throw new IllegalStateException("UserRepository no ha sido inyectado correctamente");
        }
        if (userRepository.getAllUsers().isEmpty()){
            throw new RuntimeException("No se encontro el usuario");
        }
        return userRepository.getAllUsers().stream()
                .map(String::toUpperCase)
                .toList();
    }
}
