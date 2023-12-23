package com.github.bicz.sitesculpt.page.model;

import com.github.bicz.sitesculpt.theme.model.Theme;
import com.github.bicz.sitesculpt.website.model.Website;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "title")
    @NonNull
    private String title;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "theme_id")
    private Theme pageTheme;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "website_id")
    private Website website;

    @Column(name = "place_order")
    @NonNull
    private Integer order;
}
