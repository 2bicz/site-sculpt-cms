package com.github.bicz.sitesculpt.page.repository;

import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.website.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {
    List<Optional<Page>> findAllByWebsite(Website website);
}
