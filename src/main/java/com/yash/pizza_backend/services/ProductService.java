package com.yash.pizza_backend.services;

import com.yash.pizza_backend.entities.Product;
import com.yash.pizza_backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addPizza(Product product) {
        productRepository.save(product);
    }

    public List<Product> getPizza() {
        return productRepository.findAll();
    }
}
