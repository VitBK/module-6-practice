package org.practice;

import static org.practice.TemplateConstants.CONSOLE_MODE_INTRO;
import static org.practice.TemplateConstants.FILE_MODE_INTRO;

public class AppRunner {
    
    private final ConsoleHandler consoleHandler;
    private final FilesHandler filesHandler;
    private final InputValidator inputValidator;
    private final TemplateEngine templateEngine;

    public AppRunner(ConsoleHandler consoleHandler, FilesHandler filesHandler, InputValidator inputValidator,
                     TemplateEngine templateEngine) {
        this.consoleHandler = consoleHandler;
        this.filesHandler = filesHandler;
        this.inputValidator = inputValidator;
        this.templateEngine = templateEngine;
    }
    
    public void run(String[] args) {
        if (args.length > 0) {
            runInFileMode(args);
        } else {
            runInConsoleMode();
        }
    }

    private void runInFileMode(String[] args) {
        System.out.println(FILE_MODE_INTRO);
        var rawInput = filesHandler.readInput(args[0]);
        var input = filesHandler.prepareInput(rawInput);
        inputValidator.validateInput(input);
        var message = templateEngine.createMessage(input);
        filesHandler.writeToFile(message, args[1]);
    }

    private void runInConsoleMode() {
        System.out.println(CONSOLE_MODE_INTRO);
        var rawInput = consoleHandler.readInput();
        var input = consoleHandler.prepareInput(rawInput);
        inputValidator.validateInput(input);
        consoleHandler.printToConsole(templateEngine.createMessage(input));
    }
}
