package com.example.web.controllers;

import com.example.model.Publication;
import com.example.service.PublicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Misha Malysh
 */
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PublicationAdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final PublicationService publicationService;

    public PublicationAdminController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    //display List of publication
    @GetMapping("/admin-publication")
    public String viewAddPublicationPage(Model model){
        model.addAttribute("listPublications", publicationService.getAllPublications());
        return "admin-publication";
    }

    @GetMapping("/showNewPublicationForm")
    public String showNewPublicationForm(Model model){
        Publication publication = new Publication();
        model.addAttribute("publication", publication);
        return "new_publication";
    }

    @PostMapping("/savePublication")
    public String savePublication(@ModelAttribute("publication") Publication publication,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder fileName = new StringBuilder();
        Path fileNameAndPath = Paths.get(uploadPath, file.getOriginalFilename());
        fileName.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        publication.setPhoto(file.getOriginalFilename());
        publicationService.savePublication(publication);
        return "redirect:/admin";
    }
}
