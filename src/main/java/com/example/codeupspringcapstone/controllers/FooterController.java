package com.example.codeupspringcapstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class FooterController {
    @GetMapping("/meet-the-team")
    public String meetTheTeamPage() {
        return "meet-the-team"; // Assuming there's a Thymeleaf template named "meet-the-team.html"
    }

    @GetMapping("/about-brewhop")
    public String aboutBrewHopPage() {
        return "about-brewhop"; // Thymeleaf template for About BrewHop page
    }

    @GetMapping("/terms-of-service")
    public String termsOfServicePage() {
        return "terms-of-service"; // Thymeleaf template for Terms of Service page
    }
}
