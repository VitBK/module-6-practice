package org.practice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface InputPreparator {

    default Map<String, String> prepareInput(List<String> rawInput) {
        return rawInput.stream()
                .map(line -> line.split("="))
                .filter(splittedLine -> splittedLine.length > 1)
                .collect(Collectors.toMap(line -> line[0], line -> line[1]));
    }
}
