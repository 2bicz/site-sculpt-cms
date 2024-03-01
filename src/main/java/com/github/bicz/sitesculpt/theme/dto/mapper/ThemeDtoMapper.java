package com.github.bicz.sitesculpt.theme.dto.mapper;

import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import com.github.bicz.sitesculpt.theme.dto.ThemeResponse;
import com.github.bicz.sitesculpt.theme.model.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeDtoMapper {
    public Theme mapThemeRequestToTheme(ThemeRequest request) {
        Theme result = new Theme();

        result.setWebsiteTitle(request.getWebsiteTitle());
        result.setFaviconPath(request.getFaviconPath());
        result.setLogoPath(request.getLogoPath());
        result.setFontFamily(request.getFontFamily());
        result.setFontColor(request.getFontColor());
        result.setPrimaryColor(request.getPrimaryColor());
        result.setSecondaryColor(request.getSecondaryColor());
        result.setTertiaryColor(request.getTertiaryColor());
        result.setBackgroundColor(request.getBackgroundColor());

        return result;
    }

    public ThemeResponse mapThemeToThemeResponse(Theme theme) {
        ThemeResponse result = new ThemeResponse();

        result.setWebsiteTitle(theme.getWebsiteTitle());
        result.setFaviconPath(theme.getFaviconPath());
        result.setLogoPath(theme.getLogoPath());
        result.setFontFamily(theme.getFontFamily());
        result.setFontColor(theme.getFontColor());
        result.setPrimaryColor(theme.getPrimaryColor());
        result.setSecondaryColor(theme.getSecondaryColor());
        result.setTertiaryColor(theme.getTertiaryColor());
        result.setBackgroundColor(theme.getBackgroundColor());
        result.setIsCurrent(theme.getIsCurrent());
        result.setThemeId(theme.getThemeId());

        return result;
    }
}
