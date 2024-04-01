package com.example.task3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_task3")
@Validated
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @NotNull(message = "Order date is mandatory")
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private LocalDate orderDate;

    @NotNull(message = "Order date is mandatory")
    @NotEmpty(message = "Order date is should be not empty")
    @Size(min = 10, message = "Shipping address must be greater then 10 characters long")
    @Column(name = "shipping_address")
    private String shippingAddress;

    @NotNull(message = "Total price is mandatory")
    @PositiveOrZero(message = "Total price must be greater then 0")
    @Column(name = "total_price")
    private double totalPrice;

    @NotNull(message = "Order status is mandatory")
    @NotEmpty(message = "Order status is should be not empty")
    @Column(name = "order_status")
    private String orderStatus;

}
