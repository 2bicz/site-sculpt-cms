package com.github.bicz.sitesculpt.page.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page.repository.PageRepository;
import com.github.bicz.sitesculpt.page.service.PageService;
import com.github.bicz.sitesculpt.theme.model.Theme;
import com.github.bicz.sitesculpt.theme.repository.ThemeRepository;
import com.github.bicz.sitesculpt.website.model.Website;
import com.github.bicz.sitesculpt.website.repository.WebsiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.apache.logging.log4j.util.StringBuilders.equalsIgnoreCase;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    private final ThemeRepository themeRepository;
    private final WebsiteRepository websiteRepository;
    private final PageServiceRequestValidator requestValidator;

    @Override
    public List<PageResponse> getAllPagesOfWebsite(Long websiteId) {
        ArrayList<PageResponse> result = new ArrayList<>();
        Website website = obtainExistingWebsite(websiteId);

        ArrayList<Optional<Page>> optionalPages = (ArrayList<Optional<Page>>) pageRepository.findAllByWebsite(website);
        for (Optional<Page> optionalPage : optionalPages) {
            if (optionalPage.isPresent()) {
                Page page = optionalPage.get();
                PageResponse pageResponse = new PageResponse();

                pageResponse.setPageId(page.getPageId());
                pageResponse.setTitle(page.getTitle());
                pageResponse.setPath(page.getPath());
                pageResponse.setWebsiteId(page.getWebsite().getWebsiteId());
                pageResponse.setOrder(page.getOrder());

                if (Objects.nonNull(page.getPageTheme())) {
                    pageResponse.setPageThemeId(page.getPageTheme().getThemeId());
                }

                result.add(pageResponse);
            }
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

        return new PageResponse(
                page.getPageId(),
                page.getTitle(),
                page.getPath(),
                page.getPageTheme().getThemeId(),
                page.getWebsite().getWebsiteId(),
                page.getOrder()
        );
    }

    @Override
    public Long createPage(PageRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
        requestValidator.validatePageRequest(request);
        Website website = obtainExistingWebsite(request.getWebsiteId());

        if (doesPagePathAlreadyExistsForWebsite(request.getPath(), website)) {
            throw new RequestNotCorrectException(String.format("Path %s already exists for website with id %d", request.getPath(), website.getWebsiteId()));
        }

        Page page = new Page();

        if (Objects.nonNull(request.getThemeId())) {
            Optional<Theme> optionalTheme = themeRepository.findById(request.getThemeId());
            optionalTheme.ifPresent(page::setPageTheme);
        }

        page.setTitle(request.getTitle());
        page.setPath(request.getPath());
        page.setWebsite(website);
        page.setOrder(request.getOrder());

        return pageRepository.save(page).getPageId();
    }

    @Override
    public Long updatePage(Long pageId, PageRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
        if (Objects.isNull(pageId)) {
            throw new RequestNotCorrectException("Provided page id is empty");
        }
        requestValidator.validatePageRequest(request);
        Website website = obtainExistingWebsite(request.getWebsiteId());

        Page page = obtainExistingPage(pageId);

        if (!page.getPath().equals(request.getPath())) {
            if (doesPagePathAlreadyExistsForWebsite(request.getPath(), website)) {
                throw new RequestNotCorrectException(String.format("Path %s already exists for website with id %d", request.getPath(), website.getWebsiteId()));
            }
        }

        if (Objects.nonNull(request.getThemeId())) {
            Optional<Theme> optionalTheme = themeRepository.findById(request.getThemeId());
            optionalTheme.ifPresent(page::setPageTheme);
        }

        page.setTitle(request.getTitle());
        page.setPath(request.getPath());
        page.setWebsite(website);
        page.setOrder(request.getOrder());

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

    private Website obtainExistingWebsite(Long websiteId) throws ResourceNotFoundException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if (optionalWebsite.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Website with id %d not found", websiteId));
        }
        return optionalWebsite.get();
    }

    private Boolean doesPagePathAlreadyExistsForWebsite(String path, Website website) {
        List<Page> pages = new ArrayList<>();
        if (Objects.isNull(website.getPages()) || website.getPages().isEmpty()) {
            return false;
        }
        pages = website.getPages();

        boolean result = false;
        for (Page page : pages) {
            if (path.equalsIgnoreCase(page.getPath())) {
                result = true;
                break;
            }
        }

        return result;
    }
}
