package com.github.bicz.sitesculpt.page_section.service;

import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionRequest;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.model.PageSection;

import java.util.List;

public interface PageSectionService {
    List<PageSectionResponse> getAllSectionsOfThePage(Long pageId);
    PageSectionResponse getPageSectionById(Long pageSectionId);
    Long createPageSection(PageSectionRequest request);
    Long updatePageSection(Long pageSectionId, PageSectionRequest request);
    void deletePageSectionById(Long pageSectionId);
}
