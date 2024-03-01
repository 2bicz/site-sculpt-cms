package com.github.bicz.sitesculpt.page.repository;

import com.github.bicz.sitesculpt.page.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {
    Optional<Page> findByPath(String path);
}


