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

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PageSectionDtoMapper {
    private final PageSectionRepository pageSectionRepository;
    private final PageRepository pageRepository;
    private final ThemeRepository themeRepository;

    public PageSection mapPageSectionRequestToPageSection(PageSectionRequest request) {
        Optional<Page> optionalPage = pageRepository.findById(request.getPageId());
        Optional<PageSection> optionalPageSectionParent = pageSectionRepository.findById(request.getParentPageSectionId());
        Optional<Theme> optionalTheme = themeRepository.findById(request.getThemeId());

        return new PageSection(
                optionalPage.get(),
                request.getOrder(),
                request.getWidthPct(),
                request.getHeightPct(),
                optionalTheme.get(),
                optionalPageSectionParent.get()
        );
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
