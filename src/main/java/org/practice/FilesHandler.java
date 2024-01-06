package org.practice;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FilesHandler implements InputPreparator {

    public List<String> readInput(String filePath) {
        List<String> buffer = new LinkedList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            String s;
            while (scanner.hasNext()) {
                s = scanner.nextLine();
                buffer.add(s);
            }
        } catch (Exception e) {
            throw new InputReaderException("There was an error during reading from file: " + e.getMessage());
        }
        return buffer;
    }
}
