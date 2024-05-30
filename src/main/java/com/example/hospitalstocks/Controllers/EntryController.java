package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Services.EntryService;
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

@Controller
@RequestMapping("/entries")
public class EntryController {
    private final EntryService entryService;
    private final PDFService pdfService;

    public EntryController(EntryService entryService, PDFService pdfService) {
        this.entryService = entryService;
        this.pdfService = pdfService;
    }

    @GetMapping("")
    public String getAllEntries(@RequestParam(defaultValue = "date") String sortBy,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                Model model) {
        int pageSize = 30;  // Number of items per page
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());

        Page<Entry> entryPage;
        if (startDate != null && endDate != null) {
            LocalDate start = startDate.isEmpty() ? LocalDate.parse("1900-12-12") : LocalDate.parse(startDate);
            LocalDate end = endDate.isEmpty() ? LocalDate.parse("3000-12-12") : LocalDate.parse(endDate);
            entryPage = entryService.getAllEntriesByDateRange(start, end, pageable);
        } else {
            entryPage = entryService.getAllEntries(pageable);
        }

        model.addAttribute("entryPage", entryPage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "entry-list"; // Refers to the Thymeleaf template 'entry-list.html'
    }

    @GetMapping("/{id}")
    public String getEntry(@PathVariable Long id, Model model) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute("entry", entry);
        return "entry"; // Refers to the Thymeleaf template 'entry.html'
    }

    @GetMapping("/add")
    public String addEntryForm(Model model) {
        model.addAttribute("entry", new Entry());
        return "add-entry"; // Refers to the Thymeleaf template 'add-entry.html'
    }

    @PostMapping("/add")
    public String addEntry(@ModelAttribute Entry entry) {
        entryService.saveEntry(entry);
        return "redirect:/entries";
    }

    @PutMapping("/{id}")
    public String updateEntry(@PathVariable Long id, @ModelAttribute Entry entry) {
        entry.setId(id);
        entryService.saveEntry(entry);
        return "redirect:/entries";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long id) {
        Entry entry = entryService.getEntryById(id);
        String pdfPath = pdfService.generateEntryPDF(entry);
        FileSystemResource resource = new FileSystemResource(pdfPath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=entry_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
