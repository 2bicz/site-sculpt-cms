package com.github.bicz.sitesculpt.post.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.post.dto.PostRequest;
import com.github.bicz.sitesculpt.post.dto.PostResponse;
import com.github.bicz.sitesculpt.post.dto.mapper.PostDtoMapper;
import com.github.bicz.sitesculpt.post.model.Post;
import com.github.bicz.sitesculpt.post.repository.PostRepository;
import com.github.bicz.sitesculpt.post.service.PostService;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostRequestValidator requestValidator;
    private final PostDtoMapper mapper;

    @Override
    public List<PostResponse> getAllPostsByCategories(List<Long> categoriesIds) throws RequestNotCorrectException {
        if (Objects.isNull(categoriesIds) || categoriesIds.isEmpty()) {
            throw new RequestNotCorrectException("Provided categories ids are empty");
        }

        return postRepository.findAll().stream()
                .filter(post -> post.getCategories().stream()
                        .anyMatch(category -> categoriesIds.contains(category.getCategoryId())))
                .map(mapper::mapPostToPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long postId) throws RequestNotCorrectException {
        if (Objects.isNull(postId)) {
            throw new RequestNotCorrectException("Provided post id is empty");
        }
        Post post = obtainExistingPostById(postId);
        return mapper.mapPostToPostResponse(post);
    }

    @Override
    public Long createPost(PostRequest request) {
        requestValidator.validatePostRequest(request);

        Post post = mapper.mapPostRequestToPost(request);
        String username = obtainCurrentUserName();
        Optional<User> optionalCreator = userRepository.findByUsername(username);
        if (optionalCreator.isPresent()) {
            post.setCreatedBy(optionalCreator.get());
        }
        post.setCreatedAt(new Date());

        return postRepository.save(post).getPostId();
    }

    @Override
    public Long updatePost(Long postId, PostRequest request) {
        if (Objects.isNull(postId)) {
            throw new RequestNotCorrectException("Provided post id is empty");
        }
        requestValidator.validatePostRequest(request);

        Post post = obtainExistingPostById(postId);
        Post postUpdate = mapper.mapPostRequestToPost(request);

        String username = obtainCurrentUserName();
        Optional<User> optionalModifier = userRepository.findByEmail(username);
        optionalModifier.ifPresent(post::setLastModifiedBy);
        post.setLastModifiedAt(new Date());

        post.setPage(postUpdate.getPage());
        post.setTitle(postUpdate.getTitle());
        post.setContent(postUpdate.getContent());
        post.setStatus(postUpdate.getStatus());
        post.setCategories(postUpdate.getCategories());

        return postRepository.save(post).getPostId();
    }

    private String obtainCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }

    private Post obtainExistingPostById(Long postId) throws ResourceNotFoundException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Post with id %d does not exists", postId));
        }
        return optionalPost.get();
    }
}
