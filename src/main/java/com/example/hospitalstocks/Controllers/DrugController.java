package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Services.DrugService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drug-list")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("")
    public String getAllDrugs(@RequestParam(defaultValue = "id") String sortBy, Model model) {
        List<Drug> drugs = drugService.getAllDrugs(sortBy);
        model.addAttribute("drugs", drugs);
        model.addAttribute("sortBy", sortBy);
        return "drug-list"; // Refers to the Thymeleaf template 'drug-list.html'
    }

    @GetMapping("/{id}")
    public String getDrug(@PathVariable Long id, Model model) {
        Drug drug = drugService.getDrugById(id);
        model.addAttribute("drug", drug);
        return "drug"; // Refers to the Thymeleaf template 'drug.html'
    }

    @GetMapping("/add")
    public String addDrugForm(Model model) {
        model.addAttribute("drug", new Drug());
        return "add-drug"; // Refers to the Thymeleaf template 'add-consumption.html'
    }

    @PostMapping("/add")
    public String addDrug(@ModelAttribute Drug drug) {
        drugService.saveDrug(drug);
        return "redirect:/drug-list";
    }

    // Other endpoints for update, delete
}
