package com.shubham.ecom_project.service;

import com.shubham.ecom_project.model.Product;
import com.shubham.ecom_project.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product) ;
    }

    public Product updateProduct(int id, Product product,
                                 MultipartFile imageFile) throws IOException {

        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return productRepo.save(product) ;
    }

    public void deleteProduct(int id) {

        productRepo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepo.searchProduct(keyword);
    }
}
