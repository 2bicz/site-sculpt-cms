package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.ImageGalleryComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageGalleryComponentRepository extends JpaRepository<ImageGalleryComponent, Long> {
    Optional<ImageGalleryComponent> findByPageComponent(PageComponent pageComponent);
}
