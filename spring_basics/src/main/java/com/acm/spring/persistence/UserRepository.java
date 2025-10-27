package com.acm.spring.persistence;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    static final List<String> users = new ArrayList<>();

    static{
        users.add("Andres");
        users.add("Pepe");
        users.add("Juan");
    }

    public List<String> getAllUsers(){
        return users;
    }
}
