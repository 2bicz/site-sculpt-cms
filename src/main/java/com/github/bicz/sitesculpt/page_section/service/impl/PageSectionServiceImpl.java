package com.github.bicz.sitesculpt.page_section.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionRequest;
import com.github.bicz.sitesculpt.page_section.dto.PageSectionResponse;
import com.github.bicz.sitesculpt.page_section.dto.mapper.PageSectionDtoMapper;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import com.github.bicz.sitesculpt.page_section.repository.PageSectionRepository;
import com.github.bicz.sitesculpt.page_section.service.PageSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PageSectionServiceImpl implements PageSectionService {
    private final PageSectionRequestValidator requestValidator;
    private final PageSectionDtoMapper mapper;
    private final PageSectionRepository pageSectionRepository;
    private final PageRepository pageRepository;

    @Override
    public List<PageSectionResponse> getAllSectionsOfThePage(Long pageId) throws RequestNotCorrectException {
        if (Objects.isNull(pageId)) {
            throw new RequestNotCorrectException("Provided page id is empty");
        }
        ArrayList<PageSectionResponse> result = new ArrayList<>();

        Page page = obtainExistingPage(pageId);
        ArrayList<PageSection> pageSections = (ArrayList<PageSection>) pageSectionRepository.findAllByPage(page);

        for (PageSection pageSection : pageSections) {
            if (!Objects.isNull(pageSection)) {
                result.add(mapper.mapPageSectionToPageSectionResponse(pageSection));
            }
        }

        result.sort(Comparator.comparingInt(PageSectionResponse::getOrder));

        return result;
    }

    @Override
    public PageSectionResponse getPageSectionById(Long pageSectionId) throws RequestNotCorrectException {
        if (Objects.isNull(pageSectionId)) {
            throw new RequestNotCorrectException("Provided page section id is empty");
        }
        PageSection pageSection = obtainExistingPageSection(pageSectionId);
        return mapper.mapPageSectionToPageSectionResponse(pageSection);
    }

    @Override
    public Long createPageSection(PageSectionRequest request) {
        requestValidator.validatePageSectionRequest(request);
        return pageSectionRepository.save(mapper.mapPageSectionRequestToPageSection(request)).getPageSectionId();
    }

    @Override
    public Long updatePageSection(Long pageSectionId, PageSectionRequest request) throws RequestNotCorrectException {
        PageSection pageSection = obtainExistingPageSection(pageSectionId);
        requestValidator.validatePageSectionRequest(request);

        PageSection pageSectionUpdate = mapper.mapPageSectionRequestToPageSection(request);
        pageSection.setPage(pageSectionUpdate.getPage());
        pageSection.setOrder(pageSectionUpdate.getOrder());

        return pageSectionRepository.save(pageSection).getPageSectionId();
    }

    @Override
    public void deletePageSectionById(Long pageSectionId) {
        PageSection pageSection = obtainExistingPageSection(pageSectionId);
        pageSectionRepository.deleteById(pageSection.getPageSectionId());
    }

    private Page obtainExistingPage(Long pageId) throws ResourceNotFoundException {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        if (optionalPage.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page with id %d does not exist", pageId));
        }
        return optionalPage.get();
    }

    private PageSection obtainExistingPageSection(Long pageSectionId) throws ResourceNotFoundException {
        Optional<PageSection> optionalPageSection = pageSectionRepository.findById(pageSectionId);
        if (optionalPageSection.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page section with id %d does not exits", pageSectionId));
        }
        return optionalPageSection.get();
    }
}
