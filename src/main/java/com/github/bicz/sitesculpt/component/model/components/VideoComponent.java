package com.github.bicz.sitesculpt.component.model.components;

import com.github.bicz.sitesculpt.component.model.PageComponent;
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
@Table(name = "video_components")
public class VideoComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_gallery_component_id")
    private Long videoComponentId;

    @Column(name = "src")
    private String src;

    @Column(name = "alt")
    private String alt;

    @Column(name = "max_width")
    private String maxWidth;

    @Column(name = "max_height")
    private String maxHeight;

    @Column(name = "controls")
    private Boolean controls;

    @Column(name = "auto_play")
    private Boolean autoPlay;

    @Column(name = "loop")
    private Boolean loop;

    @Column(name = "muted")
    private Boolean muted;

    @OneToOne(mappedBy = "videoComponent")
    private PageComponent pageComponent;
}
