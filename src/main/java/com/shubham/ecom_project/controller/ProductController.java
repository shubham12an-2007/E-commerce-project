package com.shubham.ecom_project.controller;

import com.shubham.ecom_project.model.Product;
import com.shubham.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService ;

    public ProductController(ProductService productService){
        this.productService = productService ;
    }


    @RequestMapping("/")
    public  String Greet(){
        return "Hello World from Java" ;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts() ;
    }
}
