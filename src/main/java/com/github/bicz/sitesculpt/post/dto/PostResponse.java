package com.github.bicz.sitesculpt.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long pageId;
    private String title;
    private String content;
    private Date createdAt;
    private Long createdById;
    private Date lastModifiedAt;
    private Long lastModifiedById;
    private String status;
    private List<Long> categoriesIds;
}
