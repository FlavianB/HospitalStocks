package com.example.hospitalstocks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hospitalstocks.Entities.AppUser;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    AppUser findByUsername(String username);
}