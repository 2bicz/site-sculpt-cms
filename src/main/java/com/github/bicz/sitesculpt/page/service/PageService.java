package com.github.bicz.sitesculpt.page.service;

import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;

import java.util.List;

public interface PageService {
    List<PageResponse> getAllPages();
    PageResponse getPageById(Long pageId);
    Long createPage(PageRequest request);
    Long updatePage(Long pageId, PageRequest request);
    void deletePage(Long pageId);
}
