package com.github.bicz.sitesculpt.page.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private String title;
    private Long themeId;
    private Long websiteId;
    private Integer order;
}
