package com.iit.flightbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("title", "Welcome to Travel Dashboard");
        model.addAttribute("description", "Explore top destinations and packages");
        return "dashboard"; // dashboard.html (Thymeleaf template)
    }
}