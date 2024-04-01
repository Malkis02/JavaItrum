package com.example.task3.services;

import com.example.task3.exception.NotFoundException;
import com.example.task3.models.Product;
import com.example.task3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id=" + productId + " was not found"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        if(productRepository.existsById(productId)){
            throw new NotFoundException("Product with id=" + productId + " was not found");
        }
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product,int productId){
        if(!productRepository.existsById(productId)){
            throw new NotFoundException("Product with id=" + productId + " was not found");
        }
        return new Product(product.getName(), product.getDescription()
                , product.getPrice(), product.getQuantityInStock());
    }
}
