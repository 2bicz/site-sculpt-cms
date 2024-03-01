package com.github.bicz.sitesculpt.theme.service;

import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import com.github.bicz.sitesculpt.theme.dto.ThemeResponse;

import java.util.List;

public interface ThemeService {
    ThemeResponse getCurrentTheme();
    List<ThemeResponse> getAllThemes();
    ThemeResponse getThemeById(Long themeId);
    Long createTheme(ThemeRequest request);
    Long updateTheme(Long themeId, ThemeRequest request);
    void deleteThemeById(Long themeId);
}
