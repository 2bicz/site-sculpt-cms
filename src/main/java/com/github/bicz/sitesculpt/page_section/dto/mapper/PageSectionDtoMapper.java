package com.github.bicz.sitesculpt.page_section.dto.mapper;

import com.github.bicz.sitesculpt.component.dto.GeneratedPageComponentResponse;
import com.github.bicz.sitesculpt.component.dto.mapper.PageComponentDtoMapper;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.page_section.dto.GeneratedPageSectionResponse;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionRequest;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PageSectionDtoMapper {
    private final PageRepository pageRepository;
    private final PageComponentDtoMapper pageComponentDtoMapper;

    public PageSection mapPageSectionRequestToPageSection(PageSectionRequest request) {
        PageSection pageSection = new PageSection();
        pageSection.setOrder(request.getOrder());
        pageSection.setBackgroundColor(request.getBackgroundColor());

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
        response.setBackgroundColor(pageSection.getBackgroundColor());

        if (Objects.nonNull(pageSection.getComponents())) {
            response.setColumnCount(pageSection.getComponents().size());
        } else {
            response.setColumnCount(0);
        }

        return response;
    }

    public GeneratedPageSectionResponse mapPageSectionToGeneratedPageSectionResponse(PageSection pageSection) {
        GeneratedPageSectionResponse response = new GeneratedPageSectionResponse();
        response.setPageSectionId(pageSection.getPageSectionId());
        response.setOrder(pageSection.getOrder());
        response.setBackgroundColor(pageSection.getBackgroundColor());

        ArrayList<GeneratedPageComponentResponse> pageComponentResponses = new ArrayList<>();
        for (PageComponent component : pageSection.getComponents()) {
            pageComponentResponses.add(pageComponentDtoMapper.mapPageComponentToGeneratedPageComponentResponse(component));
        }

        pageComponentResponses.sort(Comparator.comparingInt(GeneratedPageComponentResponse::getOrder));
        response.setComponents(pageComponentResponses);

        return response;
    }
}
