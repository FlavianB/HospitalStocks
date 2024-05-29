package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Entities.Drug;
import com.example.hospitalstocks.Services.EntryService;
import com.example.hospitalstocks.Services.DrugService;
import com.example.hospitalstocks.Services.PDFService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/entries")
public class EntryController {
    private final EntryService entryService;
    private final DrugService drugService;
    private final PDFService pdfService;

    public EntryController(EntryService entryService, DrugService drugService, PDFService pdfService) {
        this.entryService = entryService;
        this.drugService = drugService;
        this.pdfService = pdfService;
    }

    @GetMapping("")
    public String getAllEntries(Model model) {
        List<Entry> entries = entryService.getAllEntries();
        List<Drug> drugs = drugService.getAllDrugs("id", "");
        model.addAttribute("entries", entries);
        model.addAttribute("drugs", drugs);
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
        List<Drug> drugs = drugService.getAllDrugs("id", "");
        model.addAttribute("drugs", drugs);
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
