package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Consumption;
import com.example.hospitalstocks.Services.PDFService;
import com.example.hospitalstocks.Services.ConsumptionService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/consumptions")
public class ConsumptionController {
    private final ConsumptionService consumptionService;
    private final PDFService pdfService;

    public ConsumptionController(ConsumptionService consumptionService, PDFService pdfService) {
        this.consumptionService = consumptionService;
        this.pdfService = pdfService;
    }

    @GetMapping("")
    public String getAllEntries(@RequestParam(defaultValue = "date") String sortBy,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                Model model) {
        int pageSize = 30;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());

        Page<Consumption> consumptionPage;
        if (startDate != null && endDate != null) {
            LocalDate start = startDate.isEmpty() ? LocalDate.parse("1900-12-12") : LocalDate.parse(startDate);
            LocalDate end = endDate.isEmpty() ? LocalDate.parse("3000-12-12") : LocalDate.parse(endDate);
            consumptionPage = consumptionService.getAllConsumptionsByDateRange(start, end, pageable);
        } else {
            consumptionPage = consumptionService.getAllConsumptions(pageable);
        }

        model.addAttribute("consumptionPage", consumptionPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "consumption-list"; // Refers to the Thymeleaf template 'consumption-list.html'
    }

    @GetMapping("/{id}")
    public String getConsumption(@PathVariable UUID id, Model model) {
        Consumption consumption = consumptionService.getConsumptionById(id);
        model.addAttribute("consumption", consumption);
        return "consumption"; // Refers to the Thymeleaf template 'consumption.html'
    }

    @GetMapping("/add")
    public String addConsumptionForm(Model model) {
        model.addAttribute("consumption", new Consumption());
        return "add-consumption"; // Refers to the Thymeleaf template 'add-consumption.html'
    }

    @PostMapping("/add")
    public String addEntry(@ModelAttribute Consumption consumption) {
        consumptionService.saveConsumption(consumption);
        return "redirect:/consumptions";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable UUID id) {
        consumptionService.deleteConsumption(id);
        return "redirect:/consumptions";
    }

    @PutMapping("/{id}")
    public String updateEntry(@PathVariable UUID id, @ModelAttribute Consumption consumption) {
        consumption.setId(id);
        consumptionService.saveConsumption(consumption);
        return "redirect:/consumptions";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable UUID id) {
        Consumption consumption = consumptionService.getConsumptionById(id);
        String pdfPath = pdfService.generateConsumptionPDF(consumption);
        FileSystemResource resource = new FileSystemResource(pdfPath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=consumption_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
