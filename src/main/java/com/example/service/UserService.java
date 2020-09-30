package com.example.service;

import com.example.model.User;
import com.example.web.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {
    User save(UserDto registrationDto);
    List<User> getAllUsers();
    User getUserById(long id);
    void saveUser(User user);
    void deleteUserBuId(long id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
