package com.github.bicz.sitesculpt.media.controller;

import com.github.bicz.sitesculpt.media.dto.MediaResponse;
import com.github.bicz.sitesculpt.media.service.MediaService;
import com.github.bicz.sitesculpt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<MediaResponse>> getMediasByUser(@RequestParam Long userId) {
        return new ResponseEntity<>(mediaService.getMediasByUser(userId), HttpStatus.OK);
    }
}
