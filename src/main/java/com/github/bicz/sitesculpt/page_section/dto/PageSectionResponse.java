package com.github.bicz.sitesculpt.page_section.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSectionResponse {
    private Long pageSectionId;
    private Long pageId;
    private Integer order;
    private Integer columnCount;
}
