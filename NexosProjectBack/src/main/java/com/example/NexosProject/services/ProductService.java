package com.example.NexosProject.services;

import com.example.NexosProject.model.Product;
import com.example.NexosProject.model.User;
import com.example.NexosProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public Product modifyProduct(Product product) {
        productRepository.deleteById(product.getId());
        productRepository.save(product);
        return product;
    }

    public boolean repeat(Product product) {
        List<Product> products = productRepository.findAll();
        if (products.size() == 1) {
            if (products.get(0).getName().equals(product.getName())) {
                return true;
            }
        } else {
            for (int i = 0; i < products.size() - 1; i++) {
                if (products.get(i).getName().equals(product.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean currentDate(Date admissionDate) {
        Date fecha = new Date();
        if (admissionDate.after(fecha)) {
            return true;
        }
        return false;
    }
}
