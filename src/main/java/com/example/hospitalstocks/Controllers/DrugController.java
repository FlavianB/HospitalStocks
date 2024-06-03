package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Entities.Supplier;
import com.example.hospitalstocks.Services.DrugService;

import com.example.hospitalstocks.Services.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    private final DrugService drugService;
    private final SupplierService supplierService;

    public DrugController(DrugService drugService, SupplierService supplierService) {
        this.drugService = drugService;
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public String getAllDrugs(@RequestParam(defaultValue = "name") String sortBy,
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

    @GetMapping("/api")
    @ResponseBody
    public Page<Drug> getAllDrugs(@RequestParam String name, @RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return drugService.getAllDrugs(name, pageable);
    }

    @GetMapping("/{id}")
    public String getDrug(@PathVariable UUID id, Model model) {
        Drug drug = drugService.getDrugById(id);
        model.addAttribute("drug", drug);
        return "drug"; // Refers to the Thymeleaf template 'drug.html'
    }

    @GetMapping("/add")
    public String showAddDrugForm(Model model) {
        Drug drug = new Drug();
        List<Supplier> suppliers = supplierService.findAll();
        model.addAttribute("drug", drug);
        model.addAttribute("suppliers", suppliers);
        return "add-drug";
    }

    @PostMapping("/add")
    public String addDrug(@ModelAttribute Drug drug) {
        drugService.saveDrug(drug);
        return "redirect:/drugs";
    }

    @PutMapping("/{id}")
    public String updateDrug(@PathVariable UUID id, @ModelAttribute Drug drug) {
        drug.setId(id);
        drugService.saveDrug(drug);
        return "redirect:/drugs";
    }
}


