package com.yash.pizza_backend.dto;

import java.util.List;

public class OrderRequestDto {

    private List<CartItemDto> items;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}
