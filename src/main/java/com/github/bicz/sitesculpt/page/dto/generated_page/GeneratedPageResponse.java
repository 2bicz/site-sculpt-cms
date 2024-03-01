package com.github.bicz.sitesculpt.page.dto.generated_page;

import com.github.bicz.sitesculpt.page_section.dto.GeneratedPageSectionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedPageResponse {
    private Long pageId;
    private Integer order;
    private List<GeneratedPageSectionResponse> sections;
    private Boolean isHeroEnabled;
    private String heroImagePath;
}
