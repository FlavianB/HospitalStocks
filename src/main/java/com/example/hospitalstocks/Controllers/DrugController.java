package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Services.DrugService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/drug-list")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("")
    public String getAllDrugs(@RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "") String name,
                              @RequestParam(defaultValue = "0") int page,
                              Model model) {
        int pageSize = 30;  // Number of items per page
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Drug> drugPage= drugService.getAllDrugs(name, pageable);

        model.addAttribute("drugPage", drugPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("name", name);
        return "drug-list"; // Refers to the Thymeleaf template 'drug-list.html'
    }

    @GetMapping("/{id}")
    public String getDrug(@PathVariable UUID id, Model model) {
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

    @PutMapping("/{id}")
    public String updateDrug(@PathVariable UUID id, @ModelAttribute Drug drug) {
        drug.setId(id);
        drugService.saveDrug(drug);
        return "redirect:/drug-list";
    }
}


