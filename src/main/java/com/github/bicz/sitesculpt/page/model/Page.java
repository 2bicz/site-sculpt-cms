package com.github.bicz.sitesculpt.page.model;

import com.github.bicz.sitesculpt.page_section.model.PageSection;
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
@Table(name = "pages", uniqueConstraints = @UniqueConstraint(columnNames = {"path", "place_order"}))
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "path")
    @NonNull
    private String path;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme pageTheme;

    @Column(name = "place_order")
    @NonNull
    private Integer order;

    @OneToMany(mappedBy = "page")
    @ToString.Exclude
    private List<PageSection> pageSections;

    @Column(name = "is_blog_page")
    private Boolean isBlogPage;
}
