package com.example.task2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String firstName;
    String lastName;
    String position;
    long salary;
    @ManyToOne
    Department department;
}
