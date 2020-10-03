package com.example.web.controllers;

import com.example.model.User;
import com.example.repository.PublicationRepository;
import com.example.service.PublicationService;
import com.example.service.UserService;
import com.example.web.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Misha Malysh
 */
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private UserService userService;
    private PublicationService publicationService;

    private final PublicationRepository publicationRepository;

    public AdminController(UserService userService, PublicationRepository publicationRepository, PublicationService publicationService) {
        this.userService = userService;
        this.publicationRepository = publicationRepository;
        this.publicationService = publicationService;
    }

    // display list of employees
    @GetMapping("/admin")
    public String viewAdminPage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewUserFrom")
    public String showNewUserFrom(Model model) {
        //create model attribute to bind from data
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDto user) {
        //save User to database
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        //get user from the service
        User user = userService.getUserById(id);
        //set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        //save User to database
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        //call delete user method
        this.userService.deleteUserBuId(id);
        return "redirect:/admin";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField") String sortField,
                                @RequestParam(value = "sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "admin";
    }

}
