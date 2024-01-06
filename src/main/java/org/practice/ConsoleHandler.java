package org.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler implements InputPreparator {

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
}
