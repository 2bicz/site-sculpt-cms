package com.github.bicz.sitesculpt.user.service;

import com.github.bicz.sitesculpt.user.dto.ResetPasswordRequest;
import com.github.bicz.sitesculpt.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
//    void resetPassword(HttpServletRequest servletRequest, ResetPasswordRequest request);
}
