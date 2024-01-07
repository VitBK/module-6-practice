package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.practice.extensions.FileOutputExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(FileOutputExtension.class)
@Tag("UnitTest")
class ConsoleHandlerTest {

    private final ConsoleHandler consoleHandler = new ConsoleHandler();

    @ParameterizedTest
    @MethodSource("getConsoleInput")
    void should_getRawInput_when_readFromConsole(String input, List<String> expected) {
        //given
        InputStream standardIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        var result = consoleHandler.readInput();

        //then
        Assertions.assertEquals(expected, result);
        System.setIn(standardIn);
    }

    @Test
    void should_printToConsole_when_messageIsGiven() {
        //given
        PrintStream standardOut = System.out;
        ByteArrayOutputStream consoleCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleCaptor));
        String message = "Test message";

        //when
        consoleHandler.printToConsole(message);

        //then
        Assertions.assertEquals(message + "\r\n", consoleCaptor.toString());
        System.setOut(standardOut);
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
