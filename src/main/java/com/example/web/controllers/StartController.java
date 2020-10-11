package com.example.web.controllers;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Misha Malysh
 */
@Controller
public class StartController {

    private UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model){
        return "/login";
    }


}
