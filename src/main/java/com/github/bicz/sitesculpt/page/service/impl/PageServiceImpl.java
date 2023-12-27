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
                result.add(new PageResponse(
                   page.getPageId(),
                   page.getTitle(),
                   page.getPageTheme().getThemeId(),
                   page.getWebsite().getWebsiteId(),
                   page.getOrder()
                ));
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
                page.getPageTheme().getThemeId(),
                page.getWebsite().getWebsiteId(),
                page.getOrder()
        );
    }

    @Override
    public Long createPage(PageRequest request) throws ResourceNotFoundException {
        requestValidator.validatePageRequest(request);

        Theme theme = obtainExistingTheme(request.getThemeId());
        Website website = obtainExistingWebsite(request.getWebsiteId());

        return pageRepository.save(new Page(request.getTitle(), theme, website, request.getOrder())).getPageId();
    }

    @Override
    public Long updatePage(Long pageId, PageRequest request) throws ResourceNotFoundException {
        if (Objects.isNull(pageId)) {
            throw new RequestNotCorrectException("Provided page id is empty");
        }
        requestValidator.validatePageRequest(request);

        Page page = obtainExistingPage(pageId);
        page.setTitle(request.getTitle());
        page.setPageTheme(obtainExistingTheme(request.getThemeId()));
        page.setWebsite(obtainExistingWebsite(request.getWebsiteId()));
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

    private Theme obtainExistingTheme(Long themeId) throws ResourceNotFoundException {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);
        if (optionalTheme.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Theme with id %d not found", themeId));
        }
        return optionalTheme.get();
    }

    private Website obtainExistingWebsite(Long websiteId) throws ResourceNotFoundException {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if (optionalWebsite.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Website with id %d not found", websiteId));
        }
        return optionalWebsite.get();
    }
}
