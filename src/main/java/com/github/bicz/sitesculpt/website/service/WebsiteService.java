package com.github.bicz.sitesculpt.website.service;

import com.github.bicz.sitesculpt.website.dto.AddAdminRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.model.Website;

import java.util.List;
import java.util.Optional;

public interface WebsiteService {
    List<WebsiteResponse> getAllWebsitesByUsername(String username);
    WebsiteResponse getWebsiteById(Long websiteId);
    Long createWebsite(WebsiteRequest request);
    void addAdminToWebsite(AddAdminRequest request);
}
