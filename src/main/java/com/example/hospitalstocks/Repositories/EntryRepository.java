package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
    Page<Entry> findAll(Pageable pageable);
    Page<Entry> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
