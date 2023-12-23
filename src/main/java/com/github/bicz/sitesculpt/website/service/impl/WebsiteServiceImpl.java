package com.github.bicz.sitesculpt.website.service.impl;

import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.model.Website;
import com.github.bicz.sitesculpt.website.repository.WebsiteRepository;
import com.github.bicz.sitesculpt.website.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {
    private final WebsiteRepository websiteRepository;

    @Override
    public WebsiteResponse getWebsiteById(Long websiteId) {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if (optionalWebsite.isEmpty()) {
            System.out.printf("website with id %d is empty\n", websiteId);
            return null;
        }
        Website website = optionalWebsite.get();

        return new WebsiteResponse(
                website.getWebsiteId(),
                website.getTitle(),
                website.getDescription(),
                website.getFaviconPath()
        );
    }

    @Override
    public Long createWebsite(WebsiteRequest request) {
        Website website = new Website();
        website.setTitle(request.getTitle());
        website.setDescription(request.getDescription());
        website.setFaviconPath(request.getFaviconPath());

        return websiteRepository.save(website).getWebsiteId();
    }
}
