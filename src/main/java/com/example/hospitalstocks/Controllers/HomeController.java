package com.example.hospitalstocks.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home"; // Refers to the Thymeleaf template 'home.html'
    }
}
