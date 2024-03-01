package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.TimelineComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimelineComponentRepository extends JpaRepository<TimelineComponent, Long> {
    Optional<TimelineComponent> findByPageComponent(PageComponent pageComponent);
}
