package com.github.bicz.sitesculpt.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long pageId;
    private String title;
    private String content;
    private String status;
    private List<Long> categoriesIds;
}
