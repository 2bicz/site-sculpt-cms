package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageComponentRepository extends JpaRepository<PageComponent, Long> {
    List<PageComponent> findAllByPageSection(PageSection pageSection);
}
