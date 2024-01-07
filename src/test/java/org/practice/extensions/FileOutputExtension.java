package org.practice.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutputExtension implements TestWatcher, TestExecutionExceptionHandler {

    private PrintWriter writer;

    public FileOutputExtension() {
        try {
            writer = new PrintWriter(new FileWriter("test-log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log("Test Successful: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log("Test Failed: " + context.getDisplayName() + " - " + cause.getMessage());
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        log("Exception: " + context.getDisplayName() + " - " + throwable.getMessage());
        throwable.printStackTrace(writer);
        writer.flush();
        throw throwable;
    }

    private void log(String message) {
        writer.println(message);
        writer.flush();
    }
}
