package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Consumption {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate date;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
