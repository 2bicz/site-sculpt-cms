package com.github.bicz.sitesculpt.post.service;

import com.github.bicz.sitesculpt.post.dto.PostRequest;
import com.github.bicz.sitesculpt.post.dto.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPostsByCategories(List<Long> categoriesIds);
    PostResponse getPostById(Long postId);
    Long createPost(PostRequest request);
    Long updatePost(Long postId, PostRequest request);
}
