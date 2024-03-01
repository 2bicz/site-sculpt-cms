package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.DescriptionCardComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescriptionCardComponentRepository extends JpaRepository<DescriptionCardComponent, Long> {
    Optional<DescriptionCardComponent> findByPageComponent(PageComponent component);
}
