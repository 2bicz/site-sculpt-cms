package com.github.bicz.sitesculpt.component.dto.mapper;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.PageComponentType;
import com.github.bicz.sitesculpt.media.repository.MediaRepository;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PageComponentDtoMapper {
    private final PageSectionRepository pageSectionRepository;
    private final MediaRepository mediaRepository;

    public PageComponent mapPageComponentRequestToPageComponent(PageComponentRequest request) {
        PageComponent pageComponent = new PageComponent();
        pageComponent.setType(PageComponentType.valueOf(request.getType()));
        pageComponent.setCustomCss(request.getCustomCss());
        pageComponent.setContent(request.getContent());

        if (Objects.nonNull(request.getPageSectionId())) {
            pageComponent.setPageSection(pageSectionRepository.findById(request.getPageSectionId()).get());
        }
        if (Objects.nonNull(request.getMediaId())) {
            pageComponent.setMedia(mediaRepository.findById(request.getMediaId()).get());
        }

        return pageComponent;
    }

    public PageComponentResponse mapPageComponentToPageComponentResponse(PageComponent pageComponent) {
        PageComponentResponse response = new PageComponentResponse();

        response.setComponentId(pageComponent.getComponentId());
        response.setType(pageComponent.getType().toString());
        response.setCustomCss(pageComponent.getCustomCss());
        response.setContent(pageComponent.getContent());

        if (Objects.nonNull(pageComponent.getPageSection())) {
            response.setPageSectionId(pageComponent.getPageSection().getPageSectionId());
        }
        if (Objects.nonNull(pageComponent.getMedia())) {
            response.setMediaId(pageComponent.getMedia().getMediaId());
        }

        return response;
    }
}
