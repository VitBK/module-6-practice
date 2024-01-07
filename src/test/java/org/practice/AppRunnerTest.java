package org.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.practice.exceptions.TagMissingException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Tag("AlmostIntegrationTest")
class AppRunnerTest {

    @InjectMocks
    private AppRunner appRunner;

    @Mock
    private ConsoleHandler consoleHandler;
    @Mock
    private FilesHandler filesHandler;
    @Spy
    private InputValidator inputValidator;
    @Mock
    private TemplateEngine templateEngine;

    @Test
    void should_runInConsoleMode_whenNoArgsProvided() {
        //given
        doNothing().when(inputValidator).validateInput(any());

        //when
        appRunner.run(new String[]{});

        //then
        verify(consoleHandler, times(1)).readInput();
        verify(consoleHandler, times(1)).prepareInput(any());
        verify(inputValidator, times(1)).validateInput(any());
        verify(templateEngine, times(1)).createMessage(any());
        verify(consoleHandler, times(1)).printToConsole(any());
    }

    @Test
    void should_runInConsoleMode_whenArgsProvided() {
        //given
        doNothing().when(inputValidator).validateInput(any());
        String[] args = {"input.txt", "output.txt"};

        //when
        appRunner.run(args);

        //then
        verify(filesHandler, times(1)).readInput(args[0]);
        verify(filesHandler, times(1)).prepareInput(any());
        verify(inputValidator, times(1)).validateInput(any());
        verify(templateEngine, times(1)).createMessage(any());
        verify(filesHandler, times(1)).writeToFile(any(), eq(args[1]));
    }

    @Test
    void should_throwException_whenInvalidInput() {
        //given
        when(filesHandler.readInput(any())).thenReturn(List.of());
        when(filesHandler.prepareInput(any())).thenCallRealMethod();
        String[] args = {"input.txt", "output.txt"};

        //when
        Assertions.assertThrows(TagMissingException.class, () -> appRunner.run(args),
                "TagMissingException should be thrown");

        //then
        verify(filesHandler, times(1)).readInput(args[0]);
        verify(filesHandler, times(1)).prepareInput(any());
        verify(inputValidator, times(1)).validateInput(any());
        verify(templateEngine, times(0)).createMessage(any());
        verify(filesHandler, times(0)).writeToFile(any(), eq(args[1]));
    }
}
