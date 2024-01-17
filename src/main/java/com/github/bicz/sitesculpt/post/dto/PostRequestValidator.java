package com.github.bicz.sitesculpt.post.dto;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.post.dto.PostRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class PostRequestValidator {
    public void validatePostRequest(PostRequest request) throws RequestNotCorrectException {
        ArrayList<String> emptyRequiredFieldsNames = new ArrayList<>();

        if (request == null) {
            throw new RequestNotCorrectException("Provided request is empty");
        }
        if (Objects.isNull(request.getTitle())) {
            emptyRequiredFieldsNames.add("title");
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


