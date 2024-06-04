package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    @Procedure(name = "updateStock")
    void updateStock();
    Page<Stock> findAll(Pageable pageable);
}
