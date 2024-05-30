package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;

import lombok.Setter;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Consumption> consumptions;
}