package com.github.bicz.sitesculpt.website.model;

import com.github.bicz.sitesculpt.page.model.Page;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "websites")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "website_id")
    private Long websiteId;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "favicon")
    private String faviconPath;

    @OneToMany(mappedBy = "website")
    private List<Page> pages;
}
