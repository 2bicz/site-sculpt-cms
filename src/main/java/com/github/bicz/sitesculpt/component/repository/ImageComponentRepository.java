package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.ImageComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageComponentRepository extends JpaRepository<ImageComponent, Long> {
    Optional<ImageComponent> findByPageComponent(PageComponent pageComponent);
}
