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
import java.util.List;
import java.util.UUID;

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
    public String getAllStocks(@RequestParam(defaultValue = "date") String sortBy,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                Model model) {
        int pageSize = 30;  // Number of items per page
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());

        Page<Stock> stockPage;
        if (startDate != null && endDate != null) {
            LocalDate start = startDate.isEmpty() ? LocalDate.parse("1900-12-12") : LocalDate.parse(startDate);
            LocalDate end = endDate.isEmpty() ? LocalDate.parse("3000-12-12") : LocalDate.parse(endDate);
            stockPage = stockService.getAllStocksByDateRange(start, end, pageable);
        } else {
            stockPage = stockService.getAllStocks(pageable);
        }

        model.addAttribute("stockPage", stockPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "stock-list"; // Refers to the Thymeleaf template 'stock-list.html'
    }

    @GetMapping("/{id}")
    public String getStock(@PathVariable UUID id, Model model) {
        Stock stock = stockService.getStockById(id);
        model.addAttribute("stock", stock);
        return "stock"; // Refers to the Thymeleaf template 'stock.html'
    }

    @GetMapping("/add")
    public String addStockForm(Model model) {
        model.addAttribute("stock", new Stock());
        return "add-stock"; // Refers to the Thymeleaf template 'add-stock.html'
    }

    @PostMapping("/add")
    public String addStock(@ModelAttribute Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/stocks";
    }

    @PutMapping("/{id}")
    public String updateStock(@PathVariable UUID id, @ModelAttribute Stock stock) {
        stock.setId(id);
        stockService.saveStock(stock);
        return "redirect:/stocks";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable UUID id) {
        Stock stock = stockService.getStockById(id);
        String pdfPath = pdfService.generateStockPDF(stock);
        FileSystemResource resource = new FileSystemResource(pdfPath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stock_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
