package com.github.bicz.sitesculpt.reaction.repository;

import com.github.bicz.sitesculpt.comment.model.Comment;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.reaction.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findAllByPost(Post post);
    List<Reaction> findAllByComment(Comment comment);
}
