package com.github.bicz.sitesculpt.component.service.impl;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.dto.mapper.PageComponentDtoMapper;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.repository.PageComponentRepository;
import com.github.bicz.sitesculpt.component.service.PageComponentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PageComponentServiceImpl implements PageComponentService {
    private final PageComponentRepository pageComponentRepository;
    private final PageSectionRepository pageSectionRepository;
    private final PageComponentRequestValidator requestValidator;
    private final PageComponentDtoMapper mapper;

    @Override
    public List<PageComponentResponse> getAllByPageSection(Long pageSectionId) {
        if (Objects.isNull(pageSectionId)) {
            throw new RequestNotCorrectException("Provided page section id is empty");
        }
        ArrayList<PageComponentResponse> result = new ArrayList<>();

        PageSection pageSection = obtainExistingPageSection(pageSectionId);
        ArrayList<PageComponent> pageComponents = (ArrayList<PageComponent>) pageComponentRepository.findAllByPageSection(pageSection);

        for (PageComponent pageComponent : pageComponents) {
            if (!Objects.isNull(pageComponent)) {
                result.add(mapper.mapPageComponentToPageComponentResponse(pageComponent));
            }
        }

        result.sort(Comparator.comparingInt(PageComponentResponse::getOrder));

        return result;
    }



    @Override
    public PageComponentResponse getComponentById(Long pageComponentId) {
        if (Objects.isNull(pageComponentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        PageComponent pageComponent = obtainExistingPageComponent(pageComponentId);

        return mapper.mapPageComponentToPageComponentResponse(pageComponent);
    }

    @Override
    public Long createComponent(PageComponentRequest request) {
        requestValidator.validatePageComponentRequest(request);
        return pageComponentRepository.save(mapper.mapPageComponentRequestToPageComponent(request)).getComponentId();
    }

    @Override
    public Long updateComponent(Long componentId, PageComponentRequest request) throws RequestNotCorrectException {
        if (Objects.isNull(componentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        requestValidator.validatePageComponentRequest(request);

        PageComponent pageComponentUpdate = mapper.mapPageComponentRequestToPageComponent(request);

        PageComponent pageComponent = obtainExistingPageComponent(componentId);
        pageComponent.setType(pageComponentUpdate.getType());
        pageComponent.setPageSection(pageComponentUpdate.getPageSection());
        pageComponent.setContent(pageComponentUpdate.getContent());
        pageComponent.setOrder(pageComponentUpdate.getOrder());

        return pageComponentRepository.save(pageComponent).getComponentId();
    }

    @Override
    public void deleteComponentById(Long componentId) throws RequestNotCorrectException {
        if (Objects.isNull(componentId)) {
            throw new RequestNotCorrectException("Provided component id is empty");
        }
        PageComponent pageComponent = obtainExistingPageComponent(componentId);
        pageComponentRepository.deleteById(pageComponent.getComponentId());
    }

    private PageComponent obtainExistingPageComponent(Long pageComponentId) throws ResourceNotFoundException {
        Optional<PageComponent> optionalPageComponent = pageComponentRepository.findById(pageComponentId);
        if (optionalPageComponent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page component with id %d does not exist", pageComponentId));
        }
        return optionalPageComponent.get();
    }

    private PageSection obtainExistingPageSection(Long pageSectionId) throws ResourceNotFoundException {
        Optional<PageSection> optionalPageSection = pageSectionRepository.findById(pageSectionId);
        if (optionalPageSection.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page section with id %d does not exist", pageSectionId));
        }
        return optionalPageSection.get();
    }
}
