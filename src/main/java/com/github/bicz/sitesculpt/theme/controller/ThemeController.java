package com.github.bicz.sitesculpt.theme.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import com.github.bicz.sitesculpt.theme.dto.ThemeResponse;
import com.github.bicz.sitesculpt.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theme")
public class ThemeController {
    @Autowired
    ThemeService themeService;

    @GetMapping("/get-all")
    ResponseEntity<List<ThemeResponse>> getAllThemes() {
        return new ResponseEntity<>(themeService.getAllThemes(), HttpStatus.OK);
    }

    @GetMapping("/{themeId}")
    ResponseEntity<?> getThemeById(@PathVariable Long themeId) {
        try {
            return new ResponseEntity<>(themeService.getThemeById(themeId), HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createTheme(@RequestBody ThemeRequest request) {
        try {
            return new ResponseEntity<>(themeService.createTheme(request), HttpStatus.CREATED);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/{themeId}")
    ResponseEntity<?> updateTheme(@PathVariable Long themeId, @RequestBody ThemeRequest request) {
        try {
            return new ResponseEntity<>(themeService.updateTheme(themeId, request), HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{themeId}")
    ResponseEntity<?> deleteTheme(@PathVariable Long themeId) {
        try {
            themeService.deleteThemeById(themeId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
