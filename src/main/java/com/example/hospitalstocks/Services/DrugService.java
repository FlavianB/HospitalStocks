package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Repositories.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DrugService {
    private final DrugRepository drugRepository;
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<Drug> getAllDrugs(String sortBy, String name) {
        if (name.isEmpty()) {
            List<Drug> drugs = drugRepository.findAll();
            switch (sortBy) {
                case "name" -> drugs.sort(Comparator.comparing(Drug::getName));
                case "manufacturer" -> drugs.sort(Comparator.comparing(Drug::getManufacturer));
                case "description" -> drugs.sort(Comparator.comparing(Drug::getDescription));
                default -> drugs.sort(Comparator.comparing(Drug::getId));
            }
            return drugs;
        }
        final String processedName = name.trim().toLowerCase();
        List<Drug> drugs = drugRepository.findAll();
        drugs = drugs.stream().filter(drug -> drug.getName().toLowerCase().contains(processedName)).collect(Collectors.toCollection(ArrayList::new));
        switch (sortBy) {
            case "name" -> drugs.sort(Comparator.comparing(Drug::getName));
            case "manufacturer" -> drugs.sort(Comparator.comparing(Drug::getManufacturer));
            case "description" -> drugs.sort(Comparator.comparing(Drug::getDescription));
            default -> drugs.sort(Comparator.comparing(Drug::getId));
        }
        return drugs;
    }

    public Drug saveDrug(Drug drug) {
        drug.setName(drug.getName().trim());
        drug.setManufacturer(drug.getManufacturer().trim());
        drug.setDescription(drug.getDescription().trim());
        return drugRepository.save(drug);
    }

    public Drug getDrugByName(String name) {
        return drugRepository.findByName(name);
    }

    public Drug getDrugById(Long id) {
        return drugRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}
