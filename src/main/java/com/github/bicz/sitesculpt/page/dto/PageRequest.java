package com.github.bicz.sitesculpt.page.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private String title;
    private String path;
    private Long themeId;
    private Integer order;
    private Boolean isBlog;
    private Boolean isHeroEnabled;
    private String heroImagePath;
}

