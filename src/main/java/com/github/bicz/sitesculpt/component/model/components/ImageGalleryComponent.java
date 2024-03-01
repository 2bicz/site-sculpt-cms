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
@Table(name = "image_gallery_components")
public class ImageGalleryComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_gallery_component_id")
    private Long imageGalleryComponentId;

    @Column(name = "image_urls")
    private List<String> imageUrls;

    @OneToOne(mappedBy = "imageGalleryComponent")
    private PageComponent pageComponent;
}
