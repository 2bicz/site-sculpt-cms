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
    private Long pageSectionId;
    private String type;
    private String content;
    private Integer order;
}
