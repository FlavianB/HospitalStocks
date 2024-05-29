package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.AppUser;
import com.example.hospitalstocks.Repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validateUser(String username, String password) {
        AppUser user = appUserRepository.findByUsername(username);
        return user != null && passwordEncoder.encode(password).equals(user.getPassword());
    }

    public void saveUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

}
