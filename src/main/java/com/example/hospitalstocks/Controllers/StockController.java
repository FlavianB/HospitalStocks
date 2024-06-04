package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Stock;
import com.example.hospitalstocks.Services.StockService;
import com.example.hospitalstocks.Services.PDFService;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;
    private final PDFService pdfService;

    public StockController(StockService stockService, PDFService pdfService) {
        this.stockService = stockService;
        this.pdfService = pdfService;
    }

    @GetMapping("")
    public String getAllStocks(@RequestParam(defaultValue = "drug.name") String sortBy,
                                @RequestParam(defaultValue = "0") int page,
                                Model model) {
        int pageSize = 30;  // Number of items per page
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());

        Page<Stock> stockPage = stockService.getAllStocks(pageable);
        model.addAttribute("stockPage", stockPage);
        model.addAttribute("sortBy", sortBy);

        return "stock-list"; // Refers to the Thymeleaf template 'stock-list.html'
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity<String> editStock(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        if (updates.containsKey("reorderLevel")) {
            int newReorderLevel = parseInt(updates.get("reorderLevel").toString(), 10);
            stockService.updateReorderLevel(id, newReorderLevel);
            return ResponseEntity.ok("Reorder level updated successfully");
        }
        return ResponseEntity.badRequest().body("Invalid request");
    }

}
