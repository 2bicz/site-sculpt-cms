package com.github.bicz.sitesculpt.component.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageComponentRequest {
    private String type;
    private Long pageSectionId;
    private Long mediaId;
    private String customCss;
    private String content;
}
