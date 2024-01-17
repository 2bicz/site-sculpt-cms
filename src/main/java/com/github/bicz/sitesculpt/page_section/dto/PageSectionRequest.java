package com.github.bicz.sitesculpt.page_section.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSectionRequest {
    private Long pageId;
    private Integer order;
}
