package com.example.hospitalstocks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hospitalstocks.Entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}