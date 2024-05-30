package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Repositories.EntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public Page<Entry> getAllEntries(Pageable pageable) {
        return  entryRepository.findAll(pageable);
    }

    public Entry getEntryById(Long id) {
        return entryRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}
