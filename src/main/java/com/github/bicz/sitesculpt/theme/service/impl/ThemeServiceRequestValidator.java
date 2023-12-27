package com.github.bicz.sitesculpt.theme.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.theme.dto.ThemeRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ThemeServiceRequestValidator {
    public void validateThemeRequest(ThemeRequest request) throws RequestNotCorrectException {
        ArrayList<String> emptyRequiredFieldsNames = new ArrayList<>();
        if (request == null) {
            throw new RequestNotCorrectException("Provided request is empty");
        }
    }
}
