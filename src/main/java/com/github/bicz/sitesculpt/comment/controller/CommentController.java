package com.github.bicz.sitesculpt.comment.controller;

import com.github.bicz.sitesculpt.comment.dto.CommentRequest;
import com.github.bicz.sitesculpt.comment.service.CommentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/get-all-by-post/{postId}")
    ResponseEntity<?> getAllCommentsByPost(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(commentService.getAllCommentsByPost(postId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{commentId}")
    ResponseEntity<?> getCommentById(@PathVariable Long commentId) {
        try {
            return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createComment(@RequestBody CommentRequest request) {
        try {
            return new ResponseEntity<>(commentService.createComment(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/update/{commentId}")
    ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        try {
            return new ResponseEntity<>(commentService.updateComment(commentId, request), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
