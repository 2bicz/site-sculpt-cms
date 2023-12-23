package com.github.bicz.sitesculpt.theme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeResponse {
    private Long themeId;
    private String fontFamily;
    private String fontColor;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String backgroundColor;
}
