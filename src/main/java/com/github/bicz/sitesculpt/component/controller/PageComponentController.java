package com.github.bicz.sitesculpt.component.controller;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.service.PageComponentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/component")
public class PageComponentController {
    @Autowired
    PageComponentService pageComponentService;

    @GetMapping("/get-all-by-page-section/{pageSectionId}")
    ResponseEntity<?> getAllComponentsOfThePageSection(@PathVariable Long pageSectionId) {
        try {
            return new ResponseEntity<>(pageComponentService.getAllByPageSection(pageSectionId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{pageComponentId}")
    ResponseEntity<?> getPageComponentById(@PathVariable Long pageComponentId) {
        try {
            return new ResponseEntity<>(pageComponentService.getComponentById(pageComponentId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createComponent(@RequestBody PageComponentRequest request) {
        try {
            return new ResponseEntity<>(pageComponentService.createComponent(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/update/{pageComponentId}")
    ResponseEntity<?> updateComponent(@PathVariable Long pageComponentId, @RequestBody PageComponentRequest request) {
        try {
            return new ResponseEntity<>(pageComponentService.updateComponent(pageComponentId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{pageComponentId}")
    ResponseEntity<?> deletePageComponentById(@PathVariable Long pageComponentId) {
        try {
            pageComponentService.deleteComponentById(pageComponentId);
            return ResponseEntity.ok().build();
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
