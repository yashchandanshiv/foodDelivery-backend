package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.dto.OrderRequestDto;
import com.yash.pizza_backend.entities.Order;
import com.yash.pizza_backend.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDto request) {

        Order savedOrder = orderService.placeOrder(request);

        return ResponseEntity.ok(savedOrder);
    }
}
