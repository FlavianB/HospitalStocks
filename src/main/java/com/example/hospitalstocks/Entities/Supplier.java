package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Supplier {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String contactDetails;

    @ManyToMany(mappedBy = "suppliers")
    private List<Drug> drugs;

    @OneToMany(mappedBy = "supplier")
    private List<Entry> entries;
}