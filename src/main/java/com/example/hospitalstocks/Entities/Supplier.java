package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactDetails;

    @OneToMany(mappedBy = "supplier")
    private List<Drug> drugs;
}