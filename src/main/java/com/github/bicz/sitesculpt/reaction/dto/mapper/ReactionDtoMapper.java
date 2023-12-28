package com.github.bicz.sitesculpt.reaction.dto.mapper;

import com.github.bicz.sitesculpt.comment.repository.CommentRepository;
import com.github.bicz.sitesculpt.post.repository.PostRepository;
import com.github.bicz.sitesculpt.reaction.dto.ReactionRequest;
import com.github.bicz.sitesculpt.reaction.dto.ReactionResponse;
import com.github.bicz.sitesculpt.reaction.model.Reaction;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReactionDtoMapper {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Reaction mapReactionRequestToReaction(ReactionRequest request) {
        Reaction reaction = new Reaction();
        reaction.setType(request.getType());

        if (Objects.nonNull(request.getPostId())) {
            reaction.setPost(postRepository.findById(request.getPostId()).get());
        }
        if (Objects.nonNull(request.getCommentId())) {
            reaction.setComment(commentRepository.findById(request.getCommentId()).get());
        }
        if (Objects.nonNull(request.getGivenById())) {
            reaction.setGivenBy(userRepository.findById(request.getGivenById()).get());
        }

        return reaction;
    }

    public ReactionResponse mapReactionToReactionResponse(Reaction reaction) {
        ReactionResponse response = new ReactionResponse();
        response.setReactionId(reaction.getReactionId());
        response.setType(reaction.getType());

        if (Objects.nonNull(reaction.getPost())) {
            response.setPostId(reaction.getPost().getPostId());
        }
        if (Objects.nonNull(reaction.getComment())) {
            response.setCommentId(reaction.getComment().getCommentId());
        }
        if (Objects.nonNull(reaction.getGivenBy())) {
            response.setGivenById(reaction.getGivenBy().getUserId());
        }

        return response;
    }
}
