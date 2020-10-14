package com.example.web.controllers;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Misha Malysh
 */
@Controller
public class ProfileController {


    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showFormForProfile(Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByLogin(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String changeBalance(@ModelAttribute("balance") int balance, Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByLogin(currentUser.getUsername());
        int currentBalance = user.getBudget();
        if(balance > 0) {
            user.setBudget(currentBalance + balance);
        }
        model.addAttribute("balance", user.getBudget());
        userRepository.save(user);
        return showFormForProfile(model, currentUser);
    }


}
