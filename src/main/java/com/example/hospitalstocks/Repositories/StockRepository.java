package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> { }
