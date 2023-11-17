package com.controller;

import com.model.Petition;
import com.model.Signature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

}
