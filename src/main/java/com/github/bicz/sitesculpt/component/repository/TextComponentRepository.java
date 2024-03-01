package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.component.model.components.TextComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TextComponentRepository extends JpaRepository<TextComponent, Long> {
    Optional<TextComponent> findByPageComponent(PageComponent pageComponent);
}
