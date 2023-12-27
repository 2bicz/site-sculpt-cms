package com.github.bicz.sitesculpt.component.service;

import com.github.bicz.sitesculpt.component.dto.PageComponentRequest;
import com.github.bicz.sitesculpt.component.dto.PageComponentResponse;

public interface PageComponentService {
    PageComponentResponse getComponentById(Long componentId);
    Long createComponent(PageComponentRequest request);
    Long updateComponent(Long componentId, PageComponentRequest request);
    void deleteComponentById(Long componentId);
}
