package com.github.bicz.sitesculpt.page.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.page.dto.PageServiceRequestValidator;
import com.github.bicz.sitesculpt.page.dto.generated_page.GeneratedPageResponse;
import com.github.bicz.sitesculpt.page_section.dto.GeneratedPageSectionResponse;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.page.service.PageService;
import com.github.bicz.sitesculpt.page_section.dto.mapper.PageSectionDtoMapper;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import com.github.bicz.sitesculpt.theme.model.Theme;
import com.github.bicz.sitesculpt.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    private final ThemeRepository themeRepository;
    private final PageServiceRequestValidator requestValidator;
    private final PageSectionDtoMapper pageSectionDtoMapper;

    @Override
    public List<PageResponse> getAllPages() {
        ArrayList<PageResponse> response = new ArrayList<>();

        List<Page> pages = pageRepository.findAll();
        for (Page page : pages) {
            PageResponse pageResponse = new PageResponse();
            pageResponse.setPageId(page.getPageId());
            pageResponse.setTitle(page.getTitle());
            pageResponse.setPath(page.getPath());
            pageResponse.setOrder(page.getOrder());
            pageResponse.setIsBlogPage(page.getIsBlogPage());
            pageResponse.setIsHeroEnabled(page.getIsHeroEnabled());
            pageResponse.setHeroImagePath(page.getHeroImagePath());

            if (Objects.nonNull(page.getPageTheme())) {
                pageResponse.setPageThemeId(page.getPageTheme().getThemeId());
            }

            response.add(pageResponse);
        }

        response.sort(Comparator.comparingInt(PageResponse::getOrder));

        return response;
    }

    @Override
    public List<String> getAllPaths() {
        List<String> result = new ArrayList<>();
        List<Page> pages = pageRepository.findAll();

        for (Page page : pages) {
            result.add(page.getPath());
        }

        return result;
    }

    @Override
    public PageResponse getPageById(Long pageId) throws ResourceNotFoundException {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        if (optionalPage.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page with id %d is empty", pageId));
        }
        Page page = optionalPage.get();

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageId(page.getPageId());
        pageResponse.setTitle(page.getTitle());
        pageResponse.setPath(page.getPath());
        pageResponse.setOrder(page.getOrder());
        pageResponse.setIsBlogPage(page.getIsBlogPage());
        pageResponse.setIsHeroEnabled(page.getIsHeroEnabled());
        pageResponse.setHeroImagePath(page.getHeroImagePath());

        if (Objects.nonNull(page.getPageTheme())) {
            pageResponse.setPageThemeId(page.getPageTheme().getThemeId());
        }

        return pageResponse;
    }

    @Override
    public GeneratedPageResponse getGeneratedPageStructure(Long pageId) {
        GeneratedPageResponse response = new GeneratedPageResponse();

        Page page = obtainExistingPage(pageId);
        List<PageSection> pageSections = page.getPageSections();

        ArrayList<GeneratedPageSectionResponse> generatedPageSections = new ArrayList<>();
        for (PageSection pageSection : pageSections) {
            generatedPageSections.add(pageSectionDtoMapper.mapPageSectionToGeneratedPageSectionResponse(pageSection));
        }

        response.setPageId(page.getPageId());
        response.setOrder(page.getOrder());
        response.setIsHeroEnabled(page.getIsHeroEnabled());
        response.setHeroImagePath(page.getHeroImagePath());

        generatedPageSections.sort(Comparator.comparingInt(GeneratedPageSectionResponse::getOrder));
        response.setSections(generatedPageSections);

        return response;
    }

    @Override
    public Long createPage(PageRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
        requestValidator.validatePageRequest(request);

        if (Objects.nonNull(request.getIsBlog()) && request.getIsBlog() && doesBlogAlreadyExist()) {
            throw new RequestNotCorrectException("Blog page already exist");
        }

        Page page = new Page();

        if (Objects.nonNull(request.getThemeId())) {
            Optional<Theme> optionalTheme = themeRepository.findById(request.getThemeId());
            optionalTheme.ifPresent(page::setPageTheme);
        }

        page.setTitle(request.getTitle());
        page.setPath(request.getPath());
        page.setOrder(request.getOrder());
        page.setIsHeroEnabled(request.getIsHeroEnabled());
        page.setHeroImagePath(request.getHeroImagePath());

        return pageRepository.save(page).getPageId();
    }

    private Boolean doesBlogAlreadyExist() {
        List<Page> pages = pageRepository.findAll();
        boolean result = false;

        for (Page page : pages) {
            if (page.getIsBlogPage()) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public Long updatePage(Long pageId, PageRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
        if (Objects.isNull(pageId)) {
            throw new RequestNotCorrectException("Provided page id is empty");
        }
        requestValidator.validatePageRequest(request);

        Page page = obtainExistingPage(pageId);

        if (Objects.nonNull(request.getThemeId())) {
            Optional<Theme> optionalTheme = themeRepository.findById(request.getThemeId());
            optionalTheme.ifPresent(page::setPageTheme);
        }

        page.setTitle(request.getTitle());
        page.setPath(request.getPath());
        page.setOrder(request.getOrder());
        page.setIsHeroEnabled(request.getIsHeroEnabled());
        page.setHeroImagePath(request.getHeroImagePath());

        return pageRepository.save(page).getPageId();
    }

    @Override
    public void deletePage(Long pageId) {
        Page page = obtainExistingPage(pageId);
        pageRepository.deleteById(page.getPageId());
    }

    private Page obtainExistingPage(Long pageId) throws ResourceNotFoundException {
        Optional<Page> optionalPage = pageRepository.findById(pageId);
        if (optionalPage.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Page with id %d not found", pageId));
        }
        return optionalPage.get();
    }
}
