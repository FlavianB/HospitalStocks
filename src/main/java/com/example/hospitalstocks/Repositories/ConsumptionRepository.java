package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Consumption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ConsumptionRepository extends JpaRepository<Consumption, UUID> {
    Page<Consumption> findAll(Pageable pageable);
    Page<Consumption> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
