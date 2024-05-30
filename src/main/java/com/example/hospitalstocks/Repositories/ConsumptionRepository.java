package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumptionRepository extends JpaRepository<Consumption, UUID> {
}
