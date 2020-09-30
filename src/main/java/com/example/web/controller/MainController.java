package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Misha Malysh
 */
@Controller
public class MainController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}