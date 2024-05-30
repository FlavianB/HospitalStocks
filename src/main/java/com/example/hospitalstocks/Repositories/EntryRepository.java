package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Page<Entry> findAll(Pageable pageable);
}
