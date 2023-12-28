package com.github.bicz.sitesculpt.reaction.service;

import com.github.bicz.sitesculpt.reaction.dto.ReactionRequest;
import com.github.bicz.sitesculpt.reaction.dto.ReactionResponse;

import java.util.List;

public interface ReactionService {
    List<ReactionResponse> getAllReactionsByPost(Long postId);
    List<ReactionResponse> getAllReactionsByComment(Long commentId);
    ReactionResponse getReactionById(Long reactionId);
    Long createReaction(ReactionRequest request);
    void deleteReactionById(Long reactionId);
}
