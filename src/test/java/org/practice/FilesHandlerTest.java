package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.practice.exceptions.HandlerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

class FilesHandlerTest {

    private final FilesHandler filesHandler = new FilesHandler();
    @TempDir
    private Path tempDir;

    @ParameterizedTest
    @MethodSource("getInputFiles")
    void should_readRawInput_when_filePathIsGiven(String fileName, List<String> expected) throws IOException {
        //given
        Path input = tempDir.resolve(fileName);
        Files.write(input, expected);

        //when
        var result = filesHandler.readInput(input.toString());

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void should_writeToFile_when_filePathIsGiven() throws IOException {
        //given
        Path output = tempDir.resolve("output.txt");
        String expected = "test";

        //when
        filesHandler.writeToFile(expected, output.toString());

        //then
        Assertions.assertEquals(expected, Files.readString(output));
    }

    @Test
    void should_throwHandlerException_when_writingToFileFailed() {
        //when
        Assertions.assertThrows(HandlerException.class, () -> filesHandler.writeToFile("test", "/"));
    }

    private static Stream<Arguments> getInputFiles() {
        return Stream.of(Arguments.of("input1.txt", List.of()),
                Arguments.of("input2.txt", List.of("{#subject}=test")),
                Arguments.of("input3.txt",
                        List.of("{#subject}=test subject", "{#line}=test line", "{#signature}=test signature")));
    }
}
