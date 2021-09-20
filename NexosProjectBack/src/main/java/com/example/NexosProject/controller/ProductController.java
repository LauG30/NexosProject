package com.example.NexosProject.controller;

import com.example.NexosProject.exception.ApiRequestException;
import com.example.NexosProject.model.Product;
import com.example.NexosProject.model.User;
import com.example.NexosProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/create/product")
    private Product saveUser(@RequestBody Product product) {
        if (productService.repeat(product)) {
            throw new ApiRequestException("El usuario ya existe");
        } else if (productService.currentDate(product.getAdmissionDate())) {
            throw new ApiRequestException("La fecha es mayor al día de hoy");
        } else {
            Product productId = productService.createProduct(product);
            return product;
        }
    }

    @GetMapping(path = "/list/product")
    private ResponseEntity<List<Product>> listAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping(path = "/delete/product")
    private ResponseEntity<Void> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/product/{id}")
    private ResponseEntity<Product> getOneProduct(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping
    public Product modifyProduct(@RequestBody Product product) {
        if (productService.currentDate(product.getAdmissionDate())) {
            throw new ApiRequestException("La fecha es mayor al día de hoy");
        }
        return productService.modifyProduct(product);
    }
}
