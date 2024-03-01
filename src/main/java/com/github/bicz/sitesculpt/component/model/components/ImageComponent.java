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
@Table(name = "image_components")
public class ImageComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_component_id")
    private Long imageComponentId;

    @Column(name = "src")
    private String src;

    @Column(name = "alt")
    private String alt;

    @Column(name = "max_width")
    private String maxWidth;

    @Column(name = "max_height")
    private String maxHeight;

    @OneToOne(mappedBy = "imageComponent")
    private PageComponent pageComponent;
}
