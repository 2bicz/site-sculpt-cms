package com.github.bicz.sitesculpt.page_section.repository;

import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageSectionRepository extends JpaRepository<PageSection, Long> {
    List<PageSection> findAllByPage(Page page);
}
