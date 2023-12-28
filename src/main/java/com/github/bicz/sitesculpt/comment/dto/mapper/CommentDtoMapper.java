package com.github.bicz.sitesculpt.comment.dto.mapper;

import com.github.bicz.sitesculpt.comment.dto.CommentRequest;
import com.github.bicz.sitesculpt.comment.dto.CommentResponse;
import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.comment.model.CommentStatus;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentDtoMapper {
    private final PostRepository postRepository;

    public Comment mapCommentRequestToComment(CommentRequest request) {
        Comment comment = new Comment();
        Optional<Post> optionalPost = postRepository.findById(request.getPostId());
        optionalPost.ifPresent(comment::setPost);

        comment.setContent(request.getContent());
        comment.setStatus(CommentStatus.valueOf(request.getStatus()));

        return comment;
    }

    public CommentResponse mapCommentToCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreatedAt(comment.getCreatedAt());
        commentResponse.setLastModifiedAt(comment.getLastModifiedAt());
        commentResponse.setStatus(commentResponse.getStatus());

        if (Objects.nonNull(comment.getCreatedBy())) {
            commentResponse.setCreatedById(comment.getCreatedBy().getUserId());
        }
        if (Objects.nonNull(comment.getPost())) {
            commentResponse.setPostId(comment.getPost().getPostId());
        }

        return commentResponse;
    }
}
