package com.github.bicz.sitesculpt.post.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.post.dto.PostRequest;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.model.PostStatus;
import com.github.bicz.sitesculpt.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/get-all-by-website/{websiteId}")
    ResponseEntity<?> getAllPostsOfWebsite(@PathVariable Long websiteId) {
        try {
            return new ResponseEntity<>(postService.getAllPostsOfWebsite(websiteId), HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{postId}")
    ResponseEntity<?> getPostById(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/all-by-categories")
    ResponseEntity<?> getAllPostsByCategories(@RequestBody List<Long> categoriesIds) {
        try {
            return new ResponseEntity<>(postService.getAllPostsByCategories(categoriesIds), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createPost(@RequestBody PostRequest request) {
        try {
            return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/update/{postId}")
    ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostRequest request) {
        try {
            return new ResponseEntity<>(postService.updatePost(postId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/archive/{postId}")
    public ResponseEntity<?> archivePost(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(postService.archivePost(postId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/share/{postId}")
    public ResponseEntity<?> sharePost(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(postService.sharePost(postId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
