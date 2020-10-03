package com.example.web.controllers;

import com.example.repository.PublicationRepository;
import com.example.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Misha Malysh
 */
@Controller
public class PeriodicalsController {

    @Value("${upload.path}")
    private String uploadPath;

    private final PublicationService publicationService;

    private final PublicationRepository publicationRepository;

    public PeriodicalsController(PublicationService publicationService, PublicationRepository publicationRepository) {
        this.publicationService = publicationService;
        this.publicationRepository = publicationRepository;
    }

    @GetMapping("/periodicals")
    public String periodicalsPage(Model model) {
        model.addAttribute("title", "Main Page");
        model.addAttribute("listPublications", publicationService.getAllPublications());
        model.addAttribute("filPath", uploadPath);
        return "periodicals";
    }

    @GetMapping("/publication/{id}")
    public String publicationBuy(@PathVariable("id") long id, Model model){
        if(!publicationRepository.existsById(id)){
            return "redirect:/periodicals";
        }
        model.addAttribute("publication", publicationService.getById(id));
        return "buy-publication";
    }

}
