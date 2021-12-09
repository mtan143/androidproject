package com.example.myproject.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private String id;
    private String username;
    private int totalPrice;
    private String status;
    private List<String> products;

    public Order() {
    }

    public Order(String id, String username, int totalPrice, String status, List<String> products) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.status = status;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
