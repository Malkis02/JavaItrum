package com.example.task3.controllers;

import com.example.task3.exception.NotFoundException;
import com.example.task3.models.Order;
import com.example.task3.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {

    private final OrderService orderService;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody String json) throws JsonProcessingException {
        Order savedOrder = om.readValue(json,Order.class);
        savedOrder.setOrderDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(savedOrder));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable int orderId) throws JsonProcessingException {
            return ResponseEntity.status(HttpStatus.OK).body(om.writeValueAsString(orderService.getOrderById(orderId)));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
