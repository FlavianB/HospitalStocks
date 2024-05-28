package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
}
