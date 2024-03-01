package com.github.bicz.sitesculpt.theme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeRequest {
    private String fontFamily;
    private String fontColor;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String backgroundColor;
    private String websiteTitle;
    private String faviconPath;
    private String logoPath;
    private Boolean isCurrent;
}
