package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Consumption;
import com.example.hospitalstocks.Repositories.ConsumptionRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ConsumptionService {
    private final ConsumptionRepository consumptionRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public Page<Consumption> getAllConsumptions(Pageable pageable) {
        return consumptionRepository.findAll(pageable);
    }

    public Page<Consumption> getAllConsumptionsByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return consumptionRepository.findAllByDateBetween(startDate, endDate, pageable);
    }

    public Consumption getConsumptionById(UUID id) {
        return consumptionRepository.findById(id).orElse(null);
    }

    public void saveConsumption(Consumption consumption) {
        consumptionRepository.save(consumption);
    }
}