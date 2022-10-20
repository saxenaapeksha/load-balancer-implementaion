package org.example;

public class MaxLimitExceeded extends Exception {
    public MaxLimitExceeded(String errorMessage) {
        super(errorMessage);
    }
}
