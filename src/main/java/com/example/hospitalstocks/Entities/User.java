package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Consumption> consumptions;
}