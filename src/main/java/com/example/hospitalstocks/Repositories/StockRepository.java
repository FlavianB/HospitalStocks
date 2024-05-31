package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    Page<Stock> findAll(Pageable pageable);
    Page<Stock> findAllByDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
