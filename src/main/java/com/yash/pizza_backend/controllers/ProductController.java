package com.yash.pizza_backend.controllers;

import com.yash.pizza_backend.entities.Product;
import com.yash.pizza_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public List<Product> getPizza(){
        return productService.getPizza();
    }

    @PostMapping
    public void addPizza(@RequestBody Product product){
        productService.addPizza(product);
    }
}
