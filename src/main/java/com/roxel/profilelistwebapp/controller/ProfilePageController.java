package com.roxel.profilelistwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfilePageController {

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable String id) {

        model.addAttribute("id", id);

        return "profile";
    }
}
