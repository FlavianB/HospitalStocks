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

//    @GetMapping("/{name}")
//    public String getDrug(@PathVariable String name, Model model) {
//        Drug drug = drugService.getDrugByName(name);
//        model.addAttribute("drug", drug);
//        return "drug"; // Refers to the Thymeleaf template 'drug.html'
//    }



    // Other endpoints for update, delete
}
