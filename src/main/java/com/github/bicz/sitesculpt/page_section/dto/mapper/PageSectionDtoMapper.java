package com.github.bicz.sitesculpt.page_section.dto.mapper;

import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionRequest;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import com.github.bicz.sitesculpt.theme.model.Theme;
import com.github.bicz.sitesculpt.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PageSectionDtoMapper {
    private final PageRepository pageRepository;

    public PageSection mapPageSectionRequestToPageSection(PageSectionRequest request) {
        PageSection pageSection = new PageSection();
        pageSection.setOrder(request.getOrder());

        if (Objects.nonNull(request.getPageId())) {
            pageSection.setPage(pageRepository.findById(request.getPageId()).get());
        }

        return pageSection;
    }

    public PageSectionResponse mapPageSectionToPageSectionResponse(PageSection pageSection) {
        PageSectionResponse response = new PageSectionResponse();
        response.setPageSectionId(pageSection.getPageSectionId());
        response.setOrder(pageSection.getOrder());
        response.setPageId(pageSection.getPage().getPageId());

        if (Objects.nonNull(pageSection.getComponents())) {
            response.setColumnCount(pageSection.getComponents().size());
        } else {
            response.setColumnCount(0);
        }

        return response;
    }
}
