package org.practice;

import org.practice.exceptions.TagMissingException;

import java.util.Map;

public class InputValidator {

    public void validateInput(Map<String, String> input) {
        if (!input.keySet().containsAll(TemplateConstants.TAGS)) {
            throw new TagMissingException();
        }
    }
}
