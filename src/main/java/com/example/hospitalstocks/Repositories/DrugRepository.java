package com.example.hospitalstocks.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.hospitalstocks.Entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    Page<Drug> findAll(Pageable pageable);
    Page<Drug> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
