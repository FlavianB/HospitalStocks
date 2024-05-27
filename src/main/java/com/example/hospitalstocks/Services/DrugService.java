package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Repositories.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    // Other CRUD operations
}
