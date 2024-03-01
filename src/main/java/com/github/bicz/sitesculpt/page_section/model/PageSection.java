package com.github.bicz.sitesculpt.page_section.model;

import com.github.bicz.sitesculpt.component.model.PageComponent;
import com.github.bicz.sitesculpt.page.model.Page;
import com.github.bicz.sitesculpt.theme.model.Theme;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "page_sections")
public class PageSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_section_id")
    private Long pageSectionId;

    @ManyToOne
    @JoinColumn(name = "page_id")
    @NonNull
    private Page page;

    @Column(name = "place_order")
    @NonNull
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme pageSectionTheme;

    @Column(name = "background_color")
    private String backgroundColor;

    @OneToMany(mappedBy = "pageSection")
    @ToString.Exclude
    private List<PageComponent> components;
}
