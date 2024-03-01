package com.github.bicz.sitesculpt.component.repository;

import com.github.bicz.sitesculpt.component.model.components.TimelineComponent;
import com.github.bicz.sitesculpt.component.model.components.TimelineComponentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineComponentEntryRepository extends JpaRepository<TimelineComponentEntry, Long> {
    List<TimelineComponentEntry> findAllByTimelineComponent(TimelineComponent timelineComponent);
}
