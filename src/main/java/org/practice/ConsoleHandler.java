package org.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleHandler {

    public static final String STOP_WORD = "exit";

    public List<String> readInput() {
        List<String> buffer = new LinkedList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String s;
            while (scanner.hasNext() && !(s = scanner.nextLine()).equalsIgnoreCase(STOP_WORD)) {
                buffer.add(s);
            }
        } catch (Exception e) {
            throw new InputReaderException("There was an error during reading from console: " + e.getMessage());
        }
        return buffer;
    }

    public Map<String, String> prepareInput(List<String> rawInput) {
        return rawInput.stream()
                .map(line -> line.split("="))
                .filter(splittedLine -> splittedLine.length > 1)
                .collect(Collectors.toMap(line -> line[0], line -> line[1]));
    }
}
