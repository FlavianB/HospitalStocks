package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> { }
