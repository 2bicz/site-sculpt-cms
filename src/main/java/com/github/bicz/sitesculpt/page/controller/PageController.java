package com.github.bicz.sitesculpt.page.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {
    @Autowired
    PageService pageService;

    @GetMapping
    ResponseEntity<List<PageResponse>> getAllPages() {
        return new ResponseEntity<>(pageService.getAllPages(), HttpStatus.OK);
    }

    @GetMapping("/{pageId}")
    ResponseEntity<?> getPageById(@PathVariable Long pageId) {
        try {
            return new ResponseEntity<>(pageService.getPageById(pageId), HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createPage(@RequestBody PageRequest request) {
        try {
            return new ResponseEntity<>(pageService.createPage(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/update/{pageId}")
    ResponseEntity<?> updatePage(@PathVariable Long pageId, @RequestBody PageRequest request) {
        try {
            return new ResponseEntity<>(pageService.updatePage(pageId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{pageId}")
    ResponseEntity<?> deletePage(@PathVariable Long pageId) {
        try {
            pageService.deletePage(pageId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
