package com.github.bicz.sitesculpt.post.repository;

import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.website.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Optional<Post>> findAllByWebsite(Website website);

}
