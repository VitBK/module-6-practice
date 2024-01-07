package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Tag("UnitTest")
class InputPreparatorTest {

    @ParameterizedTest
    @MethodSource("getRawInput")
    void should_prepareInput_when_anyRawInputIsGiven(List<String> rawInput, Map<String, String> expected) {
        //when
        var result = new InputPreparator() {
        }.prepareInput(rawInput);

        //then
        Assertions.assertEquals(expected, result);
    }

    @TestFactory
    Stream<DynamicTest> should_prepareInput_when_anyRawInputIsGivenDynamic() {
        return getRawInput().map(testCase -> dynamicTest("Test with input: " + testCase.get()[0],
                () -> returnAndAssertResult(testCase.get())));
    }

    private void returnAndAssertResult(Object[] args) {
        //given
        List<String> rawInput = (List<String>) args[0];
        Map<String, String> expected = (Map<String, String>) args[1];

        //when
        var result = new InputPreparator(){}.prepareInput(rawInput);

        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> getRawInput() {
        return Stream.of(Arguments.of(List.of("123"), Map.of()),
                Arguments.of(List.of(), Map.of()),
                Arguments.of(List.of("{#subject}=test subject", "{#line}=test line", "{#signature}=test signature"),
                        Map.of("{#subject}", "test subject",
                                "{#line}", "test line",
                                "{#signature}", "test signature")));
    }
}
