package com.github.bicz.sitesculpt.category.service.impl;

import com.github.bicz.sitesculpt.category.dto.CategoryRequest;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class CategoryRequestValidator {
    public void validateCategoryRequest(CategoryRequest request) throws RequestNotCorrectException {
        ArrayList<String> emptyRequiredFieldsNames = new ArrayList<>();

        if (request == null) {
            throw new RequestNotCorrectException("Provided request is empty");
        }

        if (Objects.isNull(request.getName())) {
            emptyRequiredFieldsNames.add("name");
        }

        if (!emptyRequiredFieldsNames.isEmpty()) {
            throw new RequestNotCorrectException(buildProvidedEmptyExceptionMessage(emptyRequiredFieldsNames));
        }
    }

    private String buildProvidedEmptyExceptionMessage(ArrayList<String> emptyFieldsNames) {
        String message = "Provided ";

        for (String emptyFieldName : emptyFieldsNames) {
            message = String.format("%s, %s", message, emptyFieldName);
        }

        if (emptyFieldsNames.size() > 1) {
            message = String.format("%s are empty", message);
        } else {
            message = String.format("%s is empty", message);
        }

        return message;
    }
}
