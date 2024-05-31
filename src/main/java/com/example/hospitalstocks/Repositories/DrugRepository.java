package com.example.hospitalstocks.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.hospitalstocks.Entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrugRepository extends JpaRepository<Drug, UUID> {
    Page<Drug> findAll(Pageable pageable);
    Page<Drug> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Drug> findByNameContainingIgnoreCaseOrMainComponentContainingIgnoreCase(String name, String mainComponent, Pageable pageable);
}
