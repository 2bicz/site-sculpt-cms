package com.github.bicz.sitesculpt.reaction.controller;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.reaction.dto.ReactionRequest;
import com.github.bicz.sitesculpt.reaction.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reaction")
public class ReactionController {
    @Autowired
    ReactionService reactionService;

    @GetMapping("/get-all-by-post/{postId}")
    ResponseEntity<?> getAllReactionsByPost(@PathVariable Long postId) {
        try {
            return new ResponseEntity<>(reactionService.getAllReactionsByPost(postId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/get-all-by-comment/{commentId}")
    ResponseEntity<?> getAllReactionsByComment(@PathVariable Long commentId) {
        try {
            return new ResponseEntity<>(reactionService.getAllReactionsByComment(commentId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{reactionId}")
    ResponseEntity<?> getReactionById(@PathVariable Long reactionId) {
        try {
            return new ResponseEntity<>(reactionService.getReactionById(reactionId), HttpStatus.OK);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping
    ResponseEntity<?> createReaction(@RequestBody ReactionRequest request) {
        try {
            return new ResponseEntity<>(reactionService.createReaction(request), HttpStatus.CREATED);
        } catch (RequestNotCorrectException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{reactionId}")
    ResponseEntity<?> deleteReactionById(@PathVariable Long reactionId) {
        try {
            reactionService.deleteReactionById(reactionId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
