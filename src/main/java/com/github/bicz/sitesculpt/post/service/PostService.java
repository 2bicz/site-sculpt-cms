package com.github.bicz.sitesculpt.post.service;

import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.post.dto.PostRequest;
import com.github.bicz.sitesculpt.post.dto.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPostsOfWebsite(Long websiteId);
    List<PostResponse> getAllPostsByCategories(List<Long> categoriesIds);
    PostResponse getPostById(Long postId);
    Long createPost(PostRequest request);
    Long updatePost(Long postId, PostRequest request);
    Long archivePost(Long postId);
    Long sharePost(Long postId);
}
