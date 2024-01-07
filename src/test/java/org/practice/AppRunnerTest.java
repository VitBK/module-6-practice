package org.practice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppRunnerTest {

    @InjectMocks
    private AppRunner appRunner;

    @Mock
    private ConsoleHandler consoleHandler;
    @Mock
    private FilesHandler filesHandler;
    @Mock
    private InputValidator inputValidator;
    @Mock
    private TemplateEngine templateEngine;

    @Test
    void should_runInConsoleMode_whenNoArgsProvided() {
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
}
