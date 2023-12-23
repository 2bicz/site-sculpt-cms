package com.github.bicz.sitesculpt.website.controller;

import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.service.WebsiteService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{websiteId}")
    public ResponseEntity<WebsiteResponse> getWebsiteById(@PathVariable Long websiteId) {
        return new ResponseEntity<>(websiteService.getWebsiteById(websiteId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createWebsite(@RequestBody WebsiteRequest request) {
        return new ResponseEntity<>(websiteService.createWebsite(request), HttpStatus.CREATED);
    }
}
