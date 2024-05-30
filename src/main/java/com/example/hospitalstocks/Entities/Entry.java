package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Entry {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate date;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
}
