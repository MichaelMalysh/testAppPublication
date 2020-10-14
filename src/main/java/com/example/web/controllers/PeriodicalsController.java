package com.example.web.controllers;

import com.example.model.Publication;
import com.example.repository.PublicationRepository;
import com.example.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Misha Malysh
 */
@Controller
public class PeriodicalsController {


    private final PublicationService publicationService;

    private final PublicationRepository publicationRepository;

    public PeriodicalsController(PublicationService publicationService, PublicationRepository publicationRepository) {
        this.publicationService = publicationService;
        this.publicationRepository = publicationRepository;
    }

    @GetMapping("/periodicals")
    public String periodicalsPage(Model model) {
        return findPaginated(1, "title", "asc", model);
//        return findPaginated(1, model);
    }

    @GetMapping("/publication/{id}")
    public String publicationBuy(@PathVariable("id") long id, Model model){
        if(!publicationRepository.existsById(id)){
            return "redirect:/periodicals";
        }
        model.addAttribute("publication", publicationService.getById(id));
        return "buy-publication";
    }

    @PostMapping("/periodicals")
    public String filterTheme(@RequestParam String filter, Model model){
        List<Publication> themes;
        if(filter != null && !filter.isEmpty()) {
            themes = publicationRepository.findByTheme(filter);
            if(themes.size() == 0){
                themes = publicationRepository.findByTitle(filter);
            }
            model.addAttribute("listPublications", themes);
        }else {
            return periodicalsPage(model);
        }
        return "periodicals";
    }

    @GetMapping("/periodicals/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField") String sortField,
                                @RequestParam(value = "sortDir") String sortDir,
                                Model model) {
        int pageSize = 4;

        Page<Publication> page = publicationService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        Page<Publication> page = publicationService.findPaginated(pageNo, pageSize);
        List<Publication> listPublications = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPublications", listPublications);
        return "periodicals";
    }

}
