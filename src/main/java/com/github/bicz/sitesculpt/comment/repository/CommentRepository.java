package com.github.bicz.sitesculpt.comment.repository;

import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
