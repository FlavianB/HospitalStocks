package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Consumption;
import com.example.hospitalstocks.Repositories.ConsumptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionService {
    private final ConsumptionRepository consumptionRepository;
    public ConsumptionService(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }

    public List<Consumption> getAllConsumptions() {
        return consumptionRepository.findAll();
    }

    public Consumption saveConsumption(Consumption consumption) {
        return consumptionRepository.save(consumption);
    }
    // Other CRUD operations
}