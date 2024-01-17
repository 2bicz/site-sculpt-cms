package com.github.bicz.sitesculpt.component.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageComponentResponse {
    private Long componentId;
    private Long pageSectionId;
    private String type;
    private String content;
    private Integer order;
}
