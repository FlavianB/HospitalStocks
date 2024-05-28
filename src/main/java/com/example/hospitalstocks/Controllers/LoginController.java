package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Repositories.AppUserRepository;
import com.example.hospitalstocks.Entities.AppUser;

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
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("*/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password) {
        AppUser user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            System.out.println(user.getPassword());
            System.out.println(passwordEncoder.encode(password));
            // User exists and password matches
            // Proceed with login logic
            return "redirect:/home";
        } else {
            // User does not exist or password does not match
            // Handle login failure
            return "redirect:/login?error=true";
        }
    }

}
