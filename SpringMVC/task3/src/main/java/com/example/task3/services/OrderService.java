package com.example.task3.services;

import com.example.task3.exception.NotFoundException;
import com.example.task3.models.Order;
import com.example.task3.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with id=" + orderId + " was not found"));
    }
}
