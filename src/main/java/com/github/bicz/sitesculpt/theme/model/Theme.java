package com.github.bicz.sitesculpt.theme.model;

import com.github.bicz.sitesculpt.page.model.Page;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "themes")
public class Theme {

    public Theme(String fontFamily, String fontColor, String primaryColor, String secondaryColor, String tertiaryColor, String backgroundColor) {
        this.fontFamily = fontFamily;
        this.fontColor = fontColor;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.tertiaryColor = tertiaryColor;
        this.backgroundColor = backgroundColor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "font_family")
    private String fontFamily;

    @Column(name = "font_color")
    private String fontColor;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @Column(name = "tertiary_color")
    private String tertiaryColor;

    @Column(name = "background_color")
    private String backgroundColor;

    @OneToMany(mappedBy = "pageTheme")
    private List<Page> pages;
}
