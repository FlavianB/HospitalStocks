package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Entry;
import com.example.hospitalstocks.Repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> getAllEntrys() {
        return entryRepository.findAll();
    }

    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public Entry getEntryById(Long id) {
        return entryRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}
