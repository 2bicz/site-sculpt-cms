package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.VideoComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoComponentRepository extends JpaRepository<VideoComponent, Long> {
    Optional<VideoComponent> findByPageComponent(PageComponent pageComponent);
}
