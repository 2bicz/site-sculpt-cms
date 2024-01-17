package com.github.bicz.sitesculpt.component.dto.mapper;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.PageComponentType;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PageComponentDtoMapper {
    private final PageSectionRepository pageSectionRepository;

    public PageComponent mapPageComponentRequestToPageComponent(PageComponentRequest request) {
        PageComponent pageComponent = new PageComponent();
        pageComponent.setType(PageComponentType.valueOf(request.getType()));
        pageComponent.setContent(request.getContent());
        pageComponent.setOrder(request.getOrder());

        if (Objects.nonNull(request.getPageSectionId())) {
            pageComponent.setPageSection(pageSectionRepository.findById(request.getPageSectionId()).get());
        }

        return pageComponent;
    }

    public PageComponentResponse mapPageComponentToPageComponentResponse(PageComponent pageComponent) {
        PageComponentResponse response = new PageComponentResponse();
        response.setComponentId(pageComponent.getComponentId());
        response.setType(pageComponent.getType().toString());
        response.setContent(pageComponent.getContent());
        response.setOrder(pageComponent.getOrder());

        response.setPageSectionId(pageComponent.getPageSection().getPageSectionId());

        return response;
    }
}
