package com.example.hospitalstocks.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Drug {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String manufacturer;
    private String mainComponent;
    private String secondaryComponent;

    @ManyToMany
    @JoinTable(
            name = "drug_supplier",
            joinColumns = @JoinColumn(name = "drug_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "drug")
    private List<Entry> entries;
}
