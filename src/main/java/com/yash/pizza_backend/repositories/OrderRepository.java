package com.yash.pizza_backend.repositories;

import com.yash.pizza_backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
