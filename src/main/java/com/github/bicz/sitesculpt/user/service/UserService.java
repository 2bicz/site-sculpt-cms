package com.github.bicz.sitesculpt.user.service;

import com.github.bicz.sitesculpt.user.dto.UserResponse;
import com.github.bicz.sitesculpt.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
}
