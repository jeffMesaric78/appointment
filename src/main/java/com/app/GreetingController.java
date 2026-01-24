package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("message", "Welcome to our website!");
        return "index"; // Thymeleaf template name (greeting.html)
    }
}