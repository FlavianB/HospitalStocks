package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String manufacturer;
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "drug")
    private List<Entry> entries;
}
