package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
}
