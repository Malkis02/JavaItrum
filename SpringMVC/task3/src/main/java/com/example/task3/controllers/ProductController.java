package com.example.task3.controllers;

import com.example.task3.exception.NotFoundException;
import com.example.task3.models.Product;
import com.example.task3.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private final ProductService productService;

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<String> getAllProducts() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(om.writeValueAsString(productService.getAllProducts()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable int productId) throws JsonProcessingException {
        Product product = productService.getProductById(productId);
            String json = om.writeValueAsString(product);
            return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid String json) throws JsonProcessingException {
        Product createdProduct = productService.save(om.readValue(json,Product.class));
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.save(createdProduct));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId, @RequestBody @Valid Product product) throws JsonProcessingException {
            Product updateProduct = productService.updateProduct(product,productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(om.writeValueAsString(updateProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
