package com.controller;

// Imports...

import com.model.Petition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private List<Petition> petitions = new ArrayList<>();

    @PostMapping
    public ModelAndView createPetition(@RequestBody MultiValueMap paramMap) {
        petitions.add(new Petition(paramMap.get("title").toString(), paramMap.get("description").toString()));
        return new ModelAndView("redirect:/petitions");
    }

    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        return ResponseEntity.ok(petitions);
    }

    // Implement other methods for searching, viewing specific petition, and signing.
}
