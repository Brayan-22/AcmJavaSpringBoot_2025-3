package com.acm.apiconnectacm.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carro implements Serializable {

    private int id;
    private int userId;
    private List<Producto> productos = new ArrayList<>();

    public Carro() {
    }

    public Carro(int id, int userId, List<Producto> productos) {
        this.id = id;
        this.userId = userId;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}