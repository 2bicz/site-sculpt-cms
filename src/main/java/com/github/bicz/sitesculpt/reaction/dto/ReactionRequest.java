package com.github.bicz.sitesculpt.reaction.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionRequest {
    private String type;
    private Long postId;
    private Long commentId;
    private Long givenById;
}
