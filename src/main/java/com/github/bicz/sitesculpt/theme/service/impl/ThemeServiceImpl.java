package com.github.bicz.sitesculpt.theme.service.impl;

import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import com.github.bicz.sitesculpt.theme.dto.ThemeResponse;
import com.github.bicz.sitesculpt.theme.dto.mapper.ThemeDtoMapper;
import com.github.bicz.sitesculpt.theme.model.Theme;
import com.github.bicz.sitesculpt.theme.repository.ThemeRepository;
import com.github.bicz.sitesculpt.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;
    private final ThemeServiceRequestValidator requestValidator;
    private final ThemeDtoMapper themeMapper;

    @Override
    public List<ThemeResponse> getAllThemes() {
        ArrayList<ThemeResponse> result = new ArrayList<>();
        ArrayList<Theme> themes = (ArrayList<Theme>) themeRepository.findAll();

        for (Theme theme : themes) {
            if (!Objects.isNull(theme)) {
                result.add(themeMapper.mapThemeToThemeResponse(theme));
            }
        }

        return result;
    }

    @Override
    public ThemeResponse getThemeById(Long themeId) {
        Theme theme = obtainExistingTheme(themeId);
        return themeMapper.mapThemeToThemeResponse(theme);
    }

    @Override
    public Long createTheme(ThemeRequest request) {
        requestValidator.validateThemeRequest(request);
        return themeRepository.save(themeMapper.mapThemeRequestToTheme(request)).getThemeId();
    }

    @Override
    public Long updateTheme(Long themeId, ThemeRequest request) throws ResourceNotFoundException {
        requestValidator.validateThemeRequest(request);

        if (themeRepository.findById(themeId).isEmpty()) {
            throw new ResourceNotFoundException(String.format("Theme with id %d does not exist", themeId));
        }
        Theme theme = themeMapper.mapThemeRequestToTheme(request);
        theme.setThemeId(themeId);

        return themeRepository.save(theme).getThemeId();
    }

    @Override
    public void deleteThemeById(Long themeId) {
        if (themeRepository.findById(themeId).isEmpty()) {
            throw new ResourceNotFoundException(String.format("Theme with id %d does not exist", themeId));
        }
        themeRepository.deleteById(themeId);
    }

    private Theme obtainExistingTheme(Long themeId) throws ResourceNotFoundException {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);
        if (optionalTheme.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Theme with id %d does not exist", themeId));
        }
        return optionalTheme.get();
    }
}
