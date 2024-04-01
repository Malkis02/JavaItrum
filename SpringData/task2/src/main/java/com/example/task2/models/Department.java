package com.example.task2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
    }
}