package com.example.hospitalstocks.Controllers;

import com.example.hospitalstocks.Services.AppUserService;
import com.example.hospitalstocks.Entities.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    private final AppUserService userService;
    private final PasswordEncoder passwordEncoder;

    public SignupController(AppUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userService.saveUser(user);
        return "redirect:/login";
    }
}

