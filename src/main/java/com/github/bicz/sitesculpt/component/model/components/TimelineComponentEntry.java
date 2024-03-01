package com.github.bicz.sitesculpt.component.model.components;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "timeline_component_entries")
public class TimelineComponentEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_component_entry_id")
    private Long timelineComponentEntryId;

    @Column(name = "label")
    private String label;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="timeline_component_id")
    private TimelineComponent timelineComponent;
}
