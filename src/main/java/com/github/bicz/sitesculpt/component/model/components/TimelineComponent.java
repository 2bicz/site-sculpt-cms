package com.github.bicz.sitesculpt.component.model.components;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "timeline_components")
public class TimelineComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeline_component_id")
    private Long timelineComponentId;

    @OneToMany(mappedBy = "timelineComponent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimelineComponentEntry> entries;

    @OneToOne(mappedBy = "timelineComponent")
    private PageComponent pageComponent;
}
