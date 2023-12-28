package com.github.bicz.sitesculpt.comment.service.impl;

import com.github.bicz.sitesculpt.comment.dto.CommentRequest;
import com.github.bicz.sitesculpt.comment.dto.CommentResponse;
import com.github.bicz.sitesculpt.comment.dto.mapper.CommentDtoMapper;
import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.comment.repository.CommentRepository;
import com.github.bicz.sitesculpt.comment.service.CommentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentRequestValidator requestValidator;
    private final CommentDtoMapper mapper;

    @Override
    public List<CommentResponse> getAllCommentsByPost(Long postId) throws RequestNotCorrectException {
        if (Objects.isNull(postId)) {
            throw new RequestNotCorrectException("Provided post id is empty");
        }
        ArrayList<CommentResponse> response = new ArrayList<>();

        Post post = obtainExistingPost(postId);
        List<Comment> comments = commentRepository.findAllByPost(post);

        for (Comment comment : comments) {
            if (Objects.nonNull(comment)) {
                response.add(mapper.mapCommentToCommentResponse(comment));
            }
        }

        return response;
    }

    @Override
    public CommentResponse getCommentById(Long commentId) throws RequestNotCorrectException {
        if (Objects.isNull(commentId)) {
            throw new RequestNotCorrectException("Provided comment id is empty");
        }
        return mapper.mapCommentToCommentResponse(obtainExistingComment(commentId));
    }

    @Override
    public Long createComment(CommentRequest request) {
        requestValidator.validateCommentRequest(request);
        return commentRepository.save(mapper.mapCommentRequestToComment(request)).getCommentId();
    }

    @Override
    public Long updateComment(Long commentId, CommentRequest request) throws RequestNotCorrectException {
        if (Objects.isNull(commentId)) {
            throw new RequestNotCorrectException("Provided comment id is empty");
        }
        requestValidator.validateCommentRequest(request);

        Comment comment = obtainExistingComment(commentId);
        Comment commentUpdate = mapper.mapCommentRequestToComment(request);

        comment.setContent(commentUpdate.getContent());
        comment.setPost(commentUpdate.getPost());
        comment.setStatus(commentUpdate.getStatus());

        return commentRepository.save(comment).getCommentId();
    }

    private Comment obtainExistingComment(Long commentId) throws ResourceNotFoundException {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Comment with id %d does not exist", commentId));
        }
        return optionalComment.get();
    }

    private Post obtainExistingPost(Long postId) throws ResourceNotFoundException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Post with id %d does not exist", postId));
        }
        return optionalPost.get();
    }
}
