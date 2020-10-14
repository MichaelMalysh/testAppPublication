package com.example.web.controllers;

import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Misha Malysh
 */
@Controller

public class StartController {

    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "/login";
    }




}
