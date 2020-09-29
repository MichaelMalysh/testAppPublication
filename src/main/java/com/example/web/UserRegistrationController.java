package com.example.web;

import com.example.service.UserService;
import com.example.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Misha Malysh
 */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService service;

    public UserRegistrationController(UserService service) {
        this.service = service;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        service.save(registrationDto);
        return "redirect:/registration?success";
    }
}
