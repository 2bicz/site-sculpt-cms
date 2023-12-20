package com.github.bicz.sitesculpt.user.service.impl;

import com.github.bicz.sitesculpt.user.dto.UserResponse;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import com.github.bicz.sitesculpt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with provided e-mail address not found"));
            }
        };
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> result = new ArrayList<>();
        ArrayList<User> allUsers = (ArrayList<User>) userRepository.findAll();

        for (User user : allUsers) {
            result.add(new UserResponse(user.getUsername(), user.getEmail()));
        }

        return result;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.printf("user with provided id %d is empty\n", userId);
            return null;
        }
        User user = optionalUser.get();

        return new UserResponse(user.getUsername(), user.getEmail());
    }
}
