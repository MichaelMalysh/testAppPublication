package com.example.service;

import com.example.model.User;
import com.example.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
