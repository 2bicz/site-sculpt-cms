package com.github.bicz.sitesculpt.user.controller;

import com.github.bicz.sitesculpt.user.dto.UserResponse;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        System.out.println("");
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
}
