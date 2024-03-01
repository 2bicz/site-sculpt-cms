package com.github.bicz.sitesculpt.page_section.dto;

import com.github.bicz.sitesculpt.component.dto.GeneratedPageComponentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedPageSectionResponse {
    private Long pageSectionId;
    private Integer order;
    private List<GeneratedPageComponentResponse> components;
    private String backgroundColor;
}
