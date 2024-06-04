package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Services.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final CSVService csvService;

    public StatisticsController(StatisticsService statisticsService, CSVService csvService) {
        this.statisticsService = statisticsService;
        this.csvService = csvService;
    }

    @GetMapping("")
    public String getStatistics(Model model) {
        List<String> drugsNearReorderLevel = statisticsService.getDrugsNearReorderLevel();
        List<String> monthlyDrugConsumption = statisticsService.getMonthlyDrugConsumption();
        List<String> monthlyDrugEntries = statisticsService.getMonthlyDrugEntries();
        List<String> topSuppliers = statisticsService.getTopSuppliers();
        model.addAttribute("drugsNearReorderLevel", drugsNearReorderLevel);
        model.addAttribute("monthlyDrugConsumption", monthlyDrugConsumption);
        model.addAttribute("monthlyDrugEntries", monthlyDrugEntries);
        model.addAttribute("topSuppliers", topSuppliers);
        return "statistics"; // Refers to the Thymeleaf template 'statistics.html'
    }

    @GetMapping("/download/{statName}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadCSVFile(@PathVariable String statName) {
        String csvPath = "";

        switch (statName) {
            case "drugsNearReorderLevel":
                csvPath = csvService.generateCSV(statisticsService.getDrugsNearReorderLevel(), "Name", "Current Quantity", "Reorder Level");
            case "monthlyDrugConsumption":
                csvPath = csvService.generateCSV(statisticsService.getMonthlyDrugConsumption(), "Name", "Month", "Quantity");
            case "monthlyDrugEntries":
                csvPath = csvService.generateCSV(statisticsService.getMonthlyDrugEntries(), "Name", "Month", "Quantity");
            case "topSuppliers":
                csvPath = csvService.generateCSV(statisticsService.getTopSuppliers(), "Name", "Total Quantity");
        }

        FileSystemResource file = new FileSystemResource(csvPath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=entry_" + ".csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }
}

