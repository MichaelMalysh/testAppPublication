package com.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Misha Malysh
 */
@Controller
public class MainController {



    @GetMapping("/")
    public String home( Model model){
        model.addAttribute("title", "Main Page");
        return "home";
    }

}
