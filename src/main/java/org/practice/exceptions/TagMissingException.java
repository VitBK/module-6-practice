package org.practice.exceptions;

public class TagMissingException extends RuntimeException {

    public TagMissingException() {
        super("At least one tag is missing in the input.");
    }
}
