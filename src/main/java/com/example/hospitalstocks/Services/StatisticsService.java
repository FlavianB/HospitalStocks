package com.example.hospitalstocks.Services;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import jakarta.persistence.Query;

import java.util.List;

@Service
public class StatisticsService{
    EntityManager entityManager;

    public StatisticsService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String getDrugsNearReorderLevel() {
        StringBuilder resultString = new StringBuilder();

        Query query = entityManager.createNativeQuery("SELECT * FROM public.drugsnearreorderlevel()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            resultString.append("Name: ").append(row[0])
                    .append(", Current Quantity: ").append(row[1])
                    .append(", Reorder Level: ").append(row[2])
                    .append("\n");
        }

        return resultString.toString();
    }

    public String getMonthlyDrugConsumption() {
        StringBuilder resultString = new StringBuilder();

        Query query = entityManager.createNativeQuery("SELECT * FROM public.monthlydrugconsumption()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            resultString.append("Name: ").append(row[0])
                    .append(", Month: ").append(row[1])
                    .append(", Quantity: ").append(row[2])
                    .append("\n");
        }

        return resultString.toString();
    }

    public String getMonthlyDrugEntries() {
        StringBuilder resultString = new StringBuilder();

        Query query = entityManager.createNativeQuery("SELECT * FROM public.monthlydrugentries()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            resultString.append("Name: ").append(row[0])
                    .append(", Month: ").append(row[1])
                    .append(", Quantity: ").append(row[2])
                    .append("\n");
        }

        return resultString.toString();
    }

    public String getTopSuppliers() {
        StringBuilder resultString = new StringBuilder();

        Query query = entityManager.createNativeQuery("SELECT * FROM public.top_suppliers()");
        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {
            resultString.append("Name: ").append(row[0])
                    .append(", Total Quantity: ").append(row[1])
                    .append("\n");
        }

        return resultString.toString();
    }
}

