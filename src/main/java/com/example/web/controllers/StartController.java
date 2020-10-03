package com.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Misha Malysh
 */
@Controller
public class StartController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
