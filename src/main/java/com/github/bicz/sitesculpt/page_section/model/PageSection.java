package com.github.bicz.sitesculpt.page_section.model;

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

    public PageSection(Page page, Integer order, Double widthPct, Double heightPct, Theme pageSectionTheme, PageSection parentPageSection) {
        this.page = page;
        this.order = order;
        this.widthPct = widthPct;
        this.heightPct = heightPct;
        this.pageSectionTheme = pageSectionTheme;
        this.parentPageSection = parentPageSection;
    }

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

    @Column(name = "width_pct")
    @NonNull
    private Double widthPct;

    @Column(name = "height_pct")
    @NonNull
    private Double heightPct;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme pageSectionTheme;

    @ManyToOne
    @JoinColumn(name = "parent_section_id")
    private PageSection parentPageSection;

    @OneToMany(mappedBy = "parentPageSection")
    private List<PageSection> childrenPageSections;
}
