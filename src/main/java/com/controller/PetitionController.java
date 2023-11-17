package com.controller;

import com.model.Petition;
import com.model.Signature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class PetitionController {

    private List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        Petition petition1 = new Petition("Unite Against Climate Crisis",
                "The world is facing an unprecedented environmental crisis");
        petition1.addSignature(new Signature("Emmet Dunne", "emmet@gmail.com"));
        Petition petition2 = new Petition("End the Conflict in Ukraine",
                "The war in Ukraine has reached a critical juncture");
        petition2.addSignature(new Signature("John Smith", "john@gmail.com"));
        petitions.addAll(List.of(petition1, petition2));
    }

    @GetMapping("/create-petition")
    public String showCreatePetitionForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }

    @PostMapping("/create-petition")
    public ModelAndView createPetition(@ModelAttribute("petition") Petition petition) {
        petitions.add(petition);
        return new ModelAndView("redirect:/view-petition/" + petition.getId());
    }

    @GetMapping("/view-petitions")
    public String showAllPetitionsPage(Model model) {
        model.addAttribute("petitions", petitions);
        return "view-petitions";
    }

    @GetMapping("/view-petition/{id}")
    public String showSinglePetitionPage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("petition", petitions.stream()
                .filter(petition -> petition.getId().equals(id))
                .findFirst()
                .orElse(null));
        model.addAttribute("signature", new Signature());
        return "view-petition";
    }

    @PostMapping("/view-petition/add-signature")
    public ModelAndView addSignatureToPetition(@RequestParam(name = "id") UUID id,
                                               @ModelAttribute("signature") Signature signature) {
        petitions.stream()
                .filter(petition -> petition.getId().equals(id))
                .findFirst()
                .ifPresent(foundPetition -> foundPetition.addSignature(signature));
        return new ModelAndView("redirect:/view-petition/" + id);
    }

    @GetMapping("/search-petitions")
    public String showSearchPetitionsPage() {
        return "search-petitions";
    }

    @PostMapping("/search-petitions")
    public String searchPetitions(@RequestParam("search") String search, Model model) {
        model.addAttribute("petitions", petitions.stream()
                .filter(petition -> petition.getTitle().toUpperCase().contains(search.toUpperCase()))
                .collect(Collectors.toList()));
        return "result-search-petition";
    }

}
