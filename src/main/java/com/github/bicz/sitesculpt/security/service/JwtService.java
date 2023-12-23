package com.github.bicz.sitesculpt.security.service;

import com.github.bicz.sitesculpt.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(User userDetails);
    Boolean isTokenValid(String token, User user);
}
