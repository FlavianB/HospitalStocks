package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Repositories.AppUserRepository;
import com.example.hospitalstocks.Entities.AppUser;

import com.example.hospitalstocks.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("*/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password) {
        if (appUserService.validateUser(username, password)){
            // User exists and password matches
            return "redirect:/home";
        } else {
            // User does not exist or password does not match
            // Handle login failure
            return "redirect:/login?error=true";
        }
    }

}
