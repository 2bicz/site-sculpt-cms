package com.github.bicz.sitesculpt.reaction.service.impl;

import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.comment.repository.CommentRepository;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.repository.PostRepository;
import com.github.bicz.sitesculpt.reaction.dto.ReactionRequest;
import com.github.bicz.sitesculpt.reaction.dto.ReactionResponse;
import com.github.bicz.sitesculpt.reaction.dto.mapper.ReactionDtoMapper;
import com.github.bicz.sitesculpt.reaction.model.Reaction;
import com.github.bicz.sitesculpt.reaction.repository.ReactionRepository;
import com.github.bicz.sitesculpt.reaction.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReactionDtoMapper mapper;
    private final ReactionRequestValidator requestValidator;

    @Override
    public List<ReactionResponse> getAllReactionsByPost(Long postId) throws RequestNotCorrectException {
        if (Objects.isNull(postId)) {
           throw new RequestNotCorrectException("Provided post id is empty");
        }
        ArrayList<ReactionResponse> response = new ArrayList<>();

        Post post = obtainExistingPostById(postId);
        ArrayList<Reaction> reactions = (ArrayList<Reaction>) reactionRepository.findAllByPost(post);

        for (Reaction reaction : reactions) {
            if (Objects.nonNull(reaction)) {
                response.add(mapper.mapReactionToReactionResponse(reaction));
            }
        }

        return response;
    }

    @Override
    public List<ReactionResponse> getAllReactionsByComment(Long commentId) throws RequestNotCorrectException {
        if (Objects.isNull(commentId)) {
            throw new RequestNotCorrectException("Provided comment id is empty");
        }
        ArrayList<ReactionResponse> response = new ArrayList<>();

        Comment comment = obtainExistingCommentById(commentId);
        ArrayList<Reaction> reactions = (ArrayList<Reaction>) reactionRepository.findAllByComment(comment);

        for (Reaction reaction : reactions) {
            if (Objects.nonNull(reaction)) {
                response.add(mapper.mapReactionToReactionResponse(reaction));
            }
        }

        return response;
    }

    @Override
    public ReactionResponse getReactionById(Long reactionId) throws RequestNotCorrectException {
        if (Objects.isNull(reactionId)) {
            throw new RequestNotCorrectException("Provided reaction id is empty");
        }
        return mapper.mapReactionToReactionResponse(obtainExistingReactionById(reactionId));
    }

    @Override
    public Long createReaction(ReactionRequest request) {
        requestValidator.validateReactionRequest(request);
        return reactionRepository.save(mapper.mapReactionRequestToReaction(request)).getReactionId();
    }

    @Override
    public void deleteReactionById(Long reactionId) {
        if (Objects.isNull(reactionId)) {
            throw new RequestNotCorrectException("Provided reaction id is empty");
        }
        Reaction reaction = obtainExistingReactionById(reactionId);
        reactionRepository.deleteById(reaction.getReactionId());
    }

    private Reaction obtainExistingReactionById(Long reactionId) throws ResourceNotFoundException {
        Optional<Reaction> optionalReaction = reactionRepository.findById(reactionId);
        if (optionalReaction.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Reaction with id %d does not exist", reactionId));
        }
        return optionalReaction.get();
    }

    private Post obtainExistingPostById(Long postId) throws ResourceNotFoundException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Post with id %d does not exist", postId));
        }
        return optionalPost.get();
    }

    private Comment obtainExistingCommentById(Long commentId) throws ResourceNotFoundException {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Comment with id %d does not exist", commentId));
        }
        return optionalComment.get();
    }
}
