package com.example.hospitalstocks.Repositories;

import com.example.hospitalstocks.Entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> { }
