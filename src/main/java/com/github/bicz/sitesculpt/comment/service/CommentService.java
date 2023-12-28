package com.github.bicz.sitesculpt.comment.service;

import com.github.bicz.sitesculpt.comment.dto.CommentRequest;
import com.github.bicz.sitesculpt.comment.dto.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAllCommentsByPost(Long postId);
    CommentResponse getCommentById(Long commentId);
    Long createComment(CommentRequest request);
    Long updateComment(Long commentId, CommentRequest request);
}
