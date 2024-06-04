package com.example.hospitalstocks.Services;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService{
    EntityManager entityManager;

    public StatisticsService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getDrugsNearReorderLevel() {
        List<String> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM public.drugsnearreorderlevel()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            String line = "Name: " + row[0] + ", Current Quantity: " + row[1] + ", Reorder Level: " + row[2];
            result.add(line);
        }

        return result;
    }

    public List<String> getMonthlyDrugConsumption() {
        List<String> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM public.monthlydrugconsumption()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            String line = "Name: " + row[0] + ", Month: " + row[1] + ", Quantity: " + row[2];
            result.add(line);
        }

        return result;
    }

    public List<String> getMonthlyDrugEntries() {
        List<String> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM public.monthlydrugentries()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            String line = "Name: " + row[0] + ", Month: " + row[1] + ", Quantity: " + row[2];
            result.add(line);
        }

        return result;
    }

    public List<String> getTopSuppliers() {
        List<String> result = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT * FROM public.top_suppliers()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            String line = "Name: " + row[0] + ", Total Quantity: " + row[1];
            result.add(line);
        }

        return result;
    }
}

