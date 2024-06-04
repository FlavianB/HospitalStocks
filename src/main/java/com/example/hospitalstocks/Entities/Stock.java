package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Stock {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    private int quantity;
    private String location;

    @OneToMany(mappedBy = "stock")
    private List<Consumption> consumptions;
}
