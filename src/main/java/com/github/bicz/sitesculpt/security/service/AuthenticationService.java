package com.github.bicz.sitesculpt.security.service;

import com.github.bicz.sitesculpt.security.dto.request.SignInRequest;
import com.github.bicz.sitesculpt.security.dto.request.SignUpRequest;
import com.github.bicz.sitesculpt.security.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
