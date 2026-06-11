package com.shubham.ecom_project.controller;

import com.shubham.ecom_project.model.Product;
import com.shubham.ecom_project.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        Product product = productService.getProduct(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {

        try {
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageProductById(@PathVariable int prodId) {

        Product product = productService.getProduct(prodId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile) ;
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id ,
                                                @RequestPart Product product ,
                                                @RequestPart MultipartFile imageFile ){

        Product product1 = null;
        try {
            product1 = productService.updateProduct(id , product , imageFile);
        } catch (IOException e) {
            return new  ResponseEntity<>("Failed to add the Product" , HttpStatus.BAD_REQUEST) ;
        }


        if(product1 != null ){
            return  new ResponseEntity<>("Updated" , HttpStatus.OK) ;
        }
        else {
            return  new ResponseEntity<>("Failed to add the Product" , HttpStatus.BAD_REQUEST) ;
        }

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int  id){

        Product product = productService.getProduct(id) ;
        if(product != null){
            productService.deleteProduct(id) ;
            return new  ResponseEntity<>("Deleted" , HttpStatus.OK) ;
        }

        else {
            return  new ResponseEntity<>("Product not Found" , HttpStatus.NOT_FOUND);
        }



    }

}