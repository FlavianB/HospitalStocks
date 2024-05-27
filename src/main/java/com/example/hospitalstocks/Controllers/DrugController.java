package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("")
    public String getAllDrugs(Model model) {
        List<Drug> drugs = drugService.getAllDrugs();
        model.addAttribute("drugs", drugs);
        return "drugs"; // Refers to the Thymeleaf template 'drugs.html'
    }

    // Other endpoints for update, delete
}
