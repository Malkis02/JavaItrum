package com.example.task3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Table(name = "c_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String username;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

}
