package com.github.bicz.sitesculpt.comment.service.impl;

import com.github.bicz.sitesculpt.comment.dto.CommentRequest;
import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class CommentRequestValidator {
    public void validateCommentRequest(CommentRequest request) throws RequestNotCorrectException {
        ArrayList<String> emptyRequiredFieldsNames = new ArrayList<>();

        if (request == null) {
            throw new RequestNotCorrectException("Provided request is empty");
        }
        if (Objects.isNull(request.getPostId())) {
            emptyRequiredFieldsNames.add("post id");
        }
        if (Objects.isNull(request.getContent())) {
            emptyRequiredFieldsNames.add("content");
        }
        if (Objects.isNull(request.getStatus())) {
            emptyRequiredFieldsNames.add("status");
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
