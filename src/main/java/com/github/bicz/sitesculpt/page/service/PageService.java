package com.github.bicz.sitesculpt.page.service;

import com.github.bicz.sitesculpt.page.dto.PageRequest;
import com.github.bicz.sitesculpt.page.dto.PageResponse;
import com.github.bicz.sitesculpt.page.dto.generated_page.GeneratedPageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PageService {
    List<PageResponse> getAllPages();
    List<String> getAllPaths();
    PageResponse getPageById(Long pageId);
    GeneratedPageResponse getGeneratedPageStructure(Long pageId);
    Long createPage(PageRequest request);
    Long updatePage(Long pageId, PageRequest request);
    void deletePage(Long pageId);
}

