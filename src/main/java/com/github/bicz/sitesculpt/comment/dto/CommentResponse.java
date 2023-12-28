package com.github.bicz.sitesculpt.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    private Date createdAt;
    private Long createdById;
    private Date lastModifiedAt;
    private Long postId;
    private String status;
}
