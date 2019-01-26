package com.roxel.profilelistwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/search/")
    public String search(Model model, @RequestParam String query) {
        model.addAttribute("query", query);
        return "search";
    }
}
