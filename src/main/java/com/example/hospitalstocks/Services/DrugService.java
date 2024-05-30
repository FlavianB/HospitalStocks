package com.example.hospitalstocks.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Drug> getAllDrugs(String sortBy, String name, Pageable pageable) {
        Page<Drug> drugsPage = name.isEmpty() ?
                drugRepository.findAll(pageable) :
                drugRepository.findByNameContainingIgnoreCase(name, pageable);
        return drugsPage;
    }

    private void sortDrugs(List<Drug> drugs, String sortBy) {
        Comparator<Drug> comparator;
        switch (sortBy) {
            case "name":
                comparator = Comparator.comparing(Drug::getName);
                break;
            case "manufacturer":
                comparator = Comparator.comparing(Drug::getManufacturer);
                break;
            case "description":
                comparator = Comparator.comparing(Drug::getDescription);
                break;
            case "mainComponent":
                comparator = Comparator.comparing(Drug::getMainComponent);
                break;
            case "secondaryComponent":
                comparator = Comparator.comparing(Drug::getSecondaryComponent);
                break;
            default:
                comparator = Comparator.comparing(Drug::getId);
        }
        drugs.sort(comparator);
    }

    public Drug saveDrug(Drug drug) {
        drug.setName(drug.getName().trim());
        drug.setManufacturer(drug.getManufacturer().trim());
        drug.setDescription(drug.getDescription().trim());
        drug.setMainComponent(drug.getMainComponent().trim());
        drug.setSecondaryComponent(drug.getSecondaryComponent().trim());
        return drugRepository.save(drug);
    }

    public Drug getDrugById(Long id) {
        return drugRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}
