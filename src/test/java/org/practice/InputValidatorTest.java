package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.practice.exceptions.TagMissingException;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @Test
    void should_validateSuccessfully_when_inputIsValid() {
        //given
        var input = TemplateConstants.TAGS.stream()
                .collect(Collectors.toMap(t -> t, t -> "test tag value"));

        //when
        Assertions.assertDoesNotThrow(() -> inputValidator.validateInput(input));
    }

    @ParameterizedTest
    @MethodSource("getInvalidInput")
    void should_throwTagMissingException_when_tagIsMissingInInput(Map<String, String> input) {
        Assertions.assertThrows(TagMissingException.class, () -> inputValidator.validateInput(input),
                "Should throw TagMissingException");
    }

    private static Stream<Map<String, String>> getInvalidInput() {
        return Stream.of(Map.of("{#subject}", "Test subject"),
                Map.of(),
                Map.of("{#subject}", "Test",
                        "{#line}", "Test",
                        "{#signatu}", "Test"));
    }
}
