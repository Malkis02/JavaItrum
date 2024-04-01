package com.example.task3.services;

import com.example.task3.exceptions.NotFoundException;
import com.example.task3.models.Product;
import com.example.task3.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;


    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with this id does not exist"));
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with this id does not exist");
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with this id does not exist");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public List<Product> getAllOrderProducts(List<Long> productIds){
        return productRepository.findAllById(productIds);
    }
}
