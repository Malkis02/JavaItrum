package com.example.task3.controllers;

import com.example.task3.exceptions.NotFoundException;
import com.example.task3.models.Customer;
import com.example.task3.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/balance/{id}")
    public Long getCustomerBalance(@PathVariable Long id){
        return customerService.getCustomerBalance(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
