package com.github.bicz.sitesculpt.security.service.impl;

import com.github.bicz.sitesculpt.security.dto.request.SignInRequest;
import com.github.bicz.sitesculpt.security.dto.request.SignUpRequest;
import com.github.bicz.sitesculpt.security.dto.response.JwtAuthenticationResponse;
import com.github.bicz.sitesculpt.security.service.AuthenticationService;
import com.github.bicz.sitesculpt.security.service.JwtService;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.model.UserRole;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwtToken).build();
    }
}
