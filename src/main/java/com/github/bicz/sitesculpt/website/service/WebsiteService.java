package com.github.bicz.sitesculpt.website.service;

import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.model.Website;

import java.util.Optional;

public interface WebsiteService {
    WebsiteResponse getWebsiteById(Long websiteId);
    Long createWebsite(WebsiteRequest request);
}
