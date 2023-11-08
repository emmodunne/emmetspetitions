package com.controller;

import com.model.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("/create-petition")
    public String showCreatePetitionForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }

    // Implement other view-related methods.
}

