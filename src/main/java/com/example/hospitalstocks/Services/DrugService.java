package com.example.hospitalstocks.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Repositories.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public Page<Drug> getAllDrugs(String name, Pageable pageable) {
        Page<Drug> drugsPage = name.isEmpty() ?
                drugRepository.findAll(pageable) :
                drugRepository.findByNameContainingIgnoreCase(name, pageable);
        return drugsPage;
    }

    public Drug saveDrug(Drug drug) {
        drug.setName(drug.getName().trim());
        drug.setManufacturer(drug.getManufacturer().trim());
        drug.setDescription(drug.getDescription().trim());
        drug.setMainComponent(drug.getMainComponent().trim());
        drug.setSecondaryComponent(drug.getSecondaryComponent().trim());
        return drugRepository.save(drug);
    }

    public Drug getDrugById(UUID id) {
        return drugRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}
