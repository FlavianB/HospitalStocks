package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Repositories.EntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Page<Entry> getAllEntries(Pageable pageable) {
        return entryRepository.findAll(pageable);
    }

    public Page<Entry> getAllEntriesByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return entryRepository.findAllByDateBetween(startDate, endDate, pageable);
    }

    public Entry getEntryById(UUID id) {
        return entryRepository.findById(id).orElse(null);
    }

    public void saveEntry(Entry entry) {
        entryRepository.save(entry);
    }
}

