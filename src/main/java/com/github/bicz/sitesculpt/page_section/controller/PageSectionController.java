package com.github.bicz.sitesculpt.page_section.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionRequest;
import com.github.bicz.sitesculpt.page_section.service.PageSectionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/page-section")
public class PageSectionController {
    @Autowired
    PageSectionService pageSectionService;

    @GetMapping("/get-all-by-page/{pageId}")
    ResponseEntity<?> getAllSectionsOfThePage(@PathVariable Long pageId) {
        try {
            return new ResponseEntity<>(pageSectionService.getAllSectionsOfThePage(pageId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{pageSectionId}")
    ResponseEntity<?> getPageSectionById(@PathVariable Long pageSectionId) {
        try {
            return new ResponseEntity<>(pageSectionService.getPageSectionById(pageSectionId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createPageSection(@RequestBody PageSectionRequest request) {
        try {
            return new ResponseEntity<>(pageSectionService.createPageSection(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/update/{pageSectionId}")
    ResponseEntity<?> updatePageSection(@PathVariable Long pageSectionId, @RequestBody PageSectionRequest request) {
        try {
            return new ResponseEntity<>(pageSectionService.updatePageSection(pageSectionId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{pageSectionId}")
    ResponseEntity<?> deletePageSectionById(@PathVariable Long pageSectionId) {
        try {
            pageSectionService.deletePageSectionById(pageSectionId);
            return ResponseEntity.ok().build();
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
