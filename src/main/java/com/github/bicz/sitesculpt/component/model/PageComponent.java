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
@Table(name = "components")
public class PageComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NonNull
    private PageComponentType type;

    @OneToOne
    @JoinColumn(name = "page_section_id")
    @RestResource(path = "componentPageSection", rel = "pageSection")
    @NonNull
    private PageSection pageSection;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @Column(name = "custom_css")
    private String customCss;

    @Column(name = "content")
    private String content;
}
