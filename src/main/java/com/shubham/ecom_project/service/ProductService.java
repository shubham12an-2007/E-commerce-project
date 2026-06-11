package com.shubham.ecom_project.service;

import com.shubham.ecom_project.model.Product;
import com.shubham.ecom_project.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo ;

    public ProductService(ProductRepo productRepo){
        this.productRepo = productRepo ;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll() ;
    }

    public Product getProduct(int id) {
        return productRepo.findById(id).orElse(new Product()) ;
    }
}
