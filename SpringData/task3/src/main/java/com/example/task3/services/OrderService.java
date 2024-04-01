package com.example.task3.services;

import com.example.task3.dto.OrderDTO;
import com.example.task3.exceptions.NotFoundException;
import com.example.task3.models.Customer;
import com.example.task3.models.Order;
import com.example.task3.models.Product;
import com.example.task3.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {

    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final ProductService productService;


    @Transactional
    public Order placeOrder(OrderDTO orderDto) {
        Customer customer = customerService.getCustomer(orderDto.getCustomerId());
        long totalAmount = 0;
        for (Long productId : orderDto.getProductIds()) {
            Product product = productService.getProductById(productId);
            totalAmount += product.getPrice();
            if (product.getQuantity() <= 0) {
                throw new IllegalStateException("Product out of stock");
            }
            product.setQuantity(product.getQuantity() - 1);
        }
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance");
        }
        customer.setBalance(customer.getBalance() - totalAmount);

        Order order = new Order();
        order.setCustomer(customer);
        List<Product> products = productService.getAllOrderProducts(orderDto.getProductIds());
        order.setProducts(products);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order with this id does not exist"));
    }

    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException("Order with this id does not exist");
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException("Order with this id does not exist");
        }
        orderRepository.deleteById(id);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
