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
    private final PageSectionRepository pageSectionRepository;
    private final PageRepository pageRepository;
    private final ThemeRepository themeRepository;

    public PageSection mapPageSectionRequestToPageSection(PageSectionRequest request) {
        PageSection pageSection = new PageSection();
        pageSection.setOrder(request.getOrder());
        pageSection.setWidthPct(request.getWidthPct());
        pageSection.setHeightPct(request.getHeightPct());

        if (Objects.nonNull(request.getPageId())) {
            pageSection.setPage(pageRepository.findById(request.getPageId()).get());
        }
        if (Objects.nonNull(request.getParentPageSectionId())) {
            pageSection.setParentPageSection(pageSectionRepository.findById(request.getParentPageSectionId()).get());
        }
        if (Objects.nonNull(request.getThemeId())) {
            pageSection.setPageSectionTheme(themeRepository.findById(request.getThemeId()).get());
        }

        return pageSection;
    }

    public PageSectionResponse mapPageSectionToPageSectionResponse(PageSection pageSection) {
        return new PageSectionResponse(
                pageSection.getPageSectionId(),
                pageSection.getPage().getPageId(),
                pageSection.getOrder(),
                pageSection.getWidthPct(),
                pageSection.getHeightPct(),
                pageSection.getPageSectionTheme().getThemeId(),
                pageSection.getParentPageSection().getPageSectionId()
        );
    }
}
