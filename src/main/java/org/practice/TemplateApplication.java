package org.practice;

public class TemplateApplication {

    public static void main(String[] args) {
        var inputValidator = new InputValidator();
        var templateEngine = new TemplateEngine();
        var filesHandler = new FilesHandler();
        var consoleHandler = new ConsoleHandler();
        var appRunner = new AppRunner(consoleHandler, filesHandler, inputValidator, templateEngine);
        appRunner.run(args);
    }
}