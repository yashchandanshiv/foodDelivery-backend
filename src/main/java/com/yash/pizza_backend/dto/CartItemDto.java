package com.yash.pizza_backend.dto;

public class CartItemDto {

    private Long id;
    private String name;
    private double price;
    private int quantity;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
