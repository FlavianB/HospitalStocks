package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> { }
