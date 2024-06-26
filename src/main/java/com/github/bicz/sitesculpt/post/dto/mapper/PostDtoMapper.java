package com.github.bicz.sitesculpt.post.dto.mapper;

import com.github.bicz.sitesculpt.category.model.Category;
import com.github.bicz.sitesculpt.category.repository.CategoryRepository;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.post.dto.PostRequest;
import com.github.bicz.sitesculpt.post.dto.PostResponse;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.model.PostStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostDtoMapper {
    private final PageRepository pageRepository;
    private final CategoryRepository categoryRepository;

    public Post mapPostRequestToPost(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setStatus(PostStatus.valueOf(request.getStatus()));

        if (Objects.nonNull(request.getPageId())) {
            post.setPage(pageRepository.findById(request.getPageId()).get());
        }

        ArrayList<Category> categories = new ArrayList<>();
        if (Objects.nonNull(request.getCategoriesIds())) {
            for (Long categoryId : request.getCategoriesIds()) {
                if (Objects.isNull(categoryId)) {
                    continue;
                }
                Optional<Category> category = categoryRepository.findById(categoryId);
                category.ifPresent(categories::add);
            }
        }
        post.setCategories(categories);

        return post;
    }

    public PostResponse mapPostToPostResponse(Post post) {
        PostResponse response = new PostResponse();

        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setLastModifiedAt(post.getLastModifiedAt());
        response.setStatus(post.getStatus().toString());

        if (Objects.nonNull(post.getPage())) {
            response.setPageId(post.getPage().getPageId());
        }
        if (Objects.nonNull(post.getCreatedBy())) {
            response.setCreatedById(post.getCreatedBy().getUserId());
        }
        if (Objects.nonNull(post.getLastModifiedBy())) {
            response.setLastModifiedById(post.getLastModifiedBy().getUserId());
        }

        ArrayList<Long> categoriesIds = new ArrayList<>();
        if (Objects.nonNull(post.getCategories())) {
            for (Category category : post.getCategories()) {
                categoriesIds.add(category.getCategoryId());
            }
        }
        response.setCategoriesIds(categoriesIds);

        return response;
    }
}
