package com.github.bicz.sitesculpt.component.model;

import com.github.bicz.sitesculpt.media.model.Media;
import com.github.bicz.sitesculpt.page_section.model.PageSection;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "components", uniqueConstraints = @UniqueConstraint(columnNames = {"place_order"}))
public class PageComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NonNull
    private PageComponentType type;

    @ManyToOne
    @JoinColumn(name = "page_section_id")
    @NonNull
    @ToString.Exclude
    private PageSection pageSection;

    @Column(name = "content")
    private String content;

    @Column(name = "place_order")
    @NonNull
    private Integer order;
}
