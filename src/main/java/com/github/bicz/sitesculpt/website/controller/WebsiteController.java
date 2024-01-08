package com.github.bicz.sitesculpt.website.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.website.dto.AddAdminRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/website")
@RequiredArgsConstructor
public class WebsiteController {
    @Autowired
    WebsiteService websiteService;

    @GetMapping("/all-by-user/{username}")
    public ResponseEntity<?> getAllWebsitesByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(websiteService.getAllWebsitesByUsername(username), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{websiteId}")
    public ResponseEntity<WebsiteResponse> getWebsiteById(@PathVariable Long websiteId) {
        return new ResponseEntity<>(websiteService.getWebsiteById(websiteId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createWebsite(@RequestBody WebsiteRequest request) {
        return new ResponseEntity<>(websiteService.createWebsite(request), HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addAdminToWebsite(@RequestBody AddAdminRequest request) {
        try {
            websiteService.addAdminToWebsite(request);
            return ResponseEntity.ok().build();
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
