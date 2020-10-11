package com.example.web.controllers;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Misha Malysh
 */
@Controller
public class ProfileController {


    private UserService userService;

    public ProfileController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String showFormForProfile(@PathVariable(value = "id") long id, Model model) {
        //get user from the service
        User user = userService.getUserById(id);
        //set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "user";
    }
}
