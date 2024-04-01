package com.example.task3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="custom_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    Customer customer;
    @OneToMany
    List<Product> products;
    long totalAmount;
}
