package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("")
    public String getStatistics(Model model) {
        String drugsNearReorderLevel = statisticsService.getDrugsNearReorderLevel();
        String monthlyDrugConsumption = statisticsService.getMonthlyDrugConsumption();
        String monthlyDrugEntries = statisticsService.getMonthlyDrugEntries();
        String monthlyDrugExpiry = statisticsService.getTopSuppliers();
        model.addAttribute("drugsNearReorderLevel", drugsNearReorderLevel);
        model.addAttribute("monthlyDrugConsumption", monthlyDrugConsumption);
        model.addAttribute("monthlyDrugEntries", monthlyDrugEntries);
        model.addAttribute("monthlyDrugExpiry", monthlyDrugExpiry);
        return "statistics"; // Refers to the Thymeleaf template 'statistics.html'
    }

}
