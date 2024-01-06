package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

class ConsoleHandlerTest {

    private final ConsoleHandler consoleHandler = new ConsoleHandler();

    @ParameterizedTest
    @MethodSource("getConsoleInput")
    void should_getRawInput_when_readFromConsole(String input, List<String> expected) {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        var result = consoleHandler.readInput();

        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> getConsoleInput() {
        return Stream.of(Arguments.of("123", List.of("123")),
                Arguments.of("", List.of()),
                Arguments.of("exit", List.of()),
                Arguments.of("""
                        {#subject}=test subject
                        {#line}=test line
                        {#signature}=test signature
                        """, List.of("{#subject}=test subject", "{#line}=test line", "{#signature}=test signature")));
    }
}
