package com.github.bicz.sitesculpt.page.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    private Long pageId;
    private String title;
    private String path;
    private Long pageThemeId;
    private Integer order;
    private Boolean isBlogPage;
    private Boolean isHeroEnabled;
    private String heroImagePath;
}
