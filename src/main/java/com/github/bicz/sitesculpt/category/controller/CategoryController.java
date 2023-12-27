package com.github.bicz.sitesculpt.category.controller;

import com.github.bicz.sitesculpt.category.dto.CategoryRequest;
import com.github.bicz.sitesculpt.category.dto.CategoryResponse;
import com.github.bicz.sitesculpt.category.service.CategoryService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/id/{categoryId}")
    ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/name/{categoryName}")
    ResponseEntity<?> getCategoryByName(@PathVariable String categoryName) {
        try {
            return new ResponseEntity<>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        try {
            return new ResponseEntity<>(categoryService.createCategory(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/update/{categoryId}")
    ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request) {
        try {
            return new ResponseEntity<>(categoryService.updateCategory(categoryId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok().build();
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
