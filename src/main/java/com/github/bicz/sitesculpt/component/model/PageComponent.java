package com.github.bicz.sitesculpt.component.model;

import com.github.bicz.sitesculpt.component.model.components.*;
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

    @ManyToOne
    @JoinColumn(name = "page_section_id")
    @NonNull
    @ToString.Exclude
    private PageSection pageSection;

    @Column(name = "place_order")
    @NonNull
    private Integer order;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "text_component_id")
    @RestResource(path = "textComponent", rel = "textComponent")
    private TextComponent textComponent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_component_id")
    @RestResource(path = "imageComponent", rel = "imageComponent")
    private ImageComponent imageComponent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_gallery_component_id")
    @RestResource(path = "imageGalleryComponent", rel = "imageGalleryComponent")
    private ImageGalleryComponent imageGalleryComponent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "video_component_id")
    @RestResource(path = "videoComponent", rel = "videoComponent")
    private VideoComponent videoComponent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "timeline_component_id")
    @RestResource(path = "timelineComponent", rel = "timelineComponent")
    private TimelineComponent timelineComponent;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_card_component_id")
    @RestResource(path = "descriptionCardComponent", rel = "descriptionCardComponent")
    private DescriptionCardComponent descriptionCardComponent;
}
