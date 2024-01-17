package com.github.bicz.sitesculpt.post.repository;

import com.github.bicz.sitesculpt.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
