package com.klagan.inditex.exception;

import java.util.Arrays;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String... requestAttributes) {
        super("Request requires all these parameters without null values:" +
            Arrays.stream(requestAttributes).reduce("", (a, b) -> a + " " + b)
        );
    }

}
