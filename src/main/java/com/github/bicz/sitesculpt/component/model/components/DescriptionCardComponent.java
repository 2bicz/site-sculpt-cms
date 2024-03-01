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
@Table(name = "description_card_components")
public class DescriptionCardComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "description_card_component_id")
    private Long descriptionCardComponentId;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "title_color")
    private String titleColor;

    @Column(name = "description")
    private String description;

    @Column(name = "description_color")
    private String descriptionColor;

    @OneToOne(mappedBy = "descriptionCardComponent")
    private PageComponent pageComponent;
}
