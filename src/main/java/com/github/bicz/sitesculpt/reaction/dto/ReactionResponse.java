package com.github.bicz.sitesculpt.reaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionResponse {
    private Long reactionId;
    private String type;
    private Long postId;
    private Long commentId;
    private Long givenById;
}
