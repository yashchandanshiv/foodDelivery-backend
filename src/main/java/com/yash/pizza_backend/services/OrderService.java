package com.yash.pizza_backend.services;

import com.yash.pizza_backend.dto.CartItemDto;
import com.yash.pizza_backend.dto.OrderRequestDto;
import com.yash.pizza_backend.entities.Order;
import com.yash.pizza_backend.entities.OrderItem;
import com.yash.pizza_backend.entities.User;
import com.yash.pizza_backend.repositories.OrderRepository;
import com.yash.pizza_backend.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order placeOrder(OrderRequestDto request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        double total = 0;

        for (CartItemDto item : request.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getId());
            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(order);

            total += item.getPrice() * item.getQuantity();

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }
}
