package com.github.bicz.sitesculpt.theme.dto.mapper;

import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import com.github.bicz.sitesculpt.theme.dto.ThemeResponse;
import com.github.bicz.sitesculpt.theme.model.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeDtoMapper {
    public Theme mapThemeRequestToTheme(ThemeRequest request) {
        return new Theme(
                request.getFontFamily(),
                request.getFontColor(),
                request.getPrimaryColor(),
                request.getSecondaryColor(),
                request.getTertiaryColor(),
                request.getBackgroundColor()
        );
    }

    public ThemeResponse mapThemeToThemeResponse(Theme theme) {
        return new ThemeResponse(
                theme.getThemeId(),
                theme.getFontFamily(),
                theme.getFontColor(),
                theme.getPrimaryColor(),
                theme.getSecondaryColor(),
                theme.getTertiaryColor(),
                theme.getBackgroundColor()
        );
    }
}
