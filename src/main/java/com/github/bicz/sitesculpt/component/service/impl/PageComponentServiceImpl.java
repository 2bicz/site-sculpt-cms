package com.github.bicz.sitesculpt.component.service.impl;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;
import com.github.bicz.sitesculpt.component.dto.mapper.PageComponentDtoMapper;
import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.repository.PageComponentRepository;
import com.github.bicz.sitesculpt.component.service.PageComponentService;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PageComponentServiceImpl implements PageComponentService {
    private final PageComponentRepository pageComponentRepository;
    private final PageComponentRequestValidator requestValidator;
    private final PageComponentDtoMapper mapper;

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
        pageComponent.setCustomCss(pageComponentUpdate.getCustomCss());
        pageComponent.setContent(pageComponentUpdate.getContent());

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
}
