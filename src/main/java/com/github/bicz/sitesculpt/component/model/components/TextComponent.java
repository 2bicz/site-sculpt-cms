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
@Table(name = "text_components")
public class TextComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_component_id")
    private Long textComponentId;

    @Column(name = "title")
    private String title;

    @Column(name = "title_color")
    private String titleColor;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "description_color")
    private String descriptionColor;

    @OneToOne(mappedBy = "textComponent")
    private PageComponent pageComponent;
}
