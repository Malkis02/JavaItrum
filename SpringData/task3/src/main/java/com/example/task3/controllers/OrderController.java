package com.example.task3.controllers;

import com.example.task3.dto.OrderDTO;
import com.example.task3.exceptions.NotFoundException;
import com.example.task3.models.Order;
import com.example.task3.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private  final OrderService orderService;


    @PostMapping
    public Order addOrder(@RequestBody OrderDTO orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id,order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
