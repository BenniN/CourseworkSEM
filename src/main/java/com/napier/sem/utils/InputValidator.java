package com.napier.sem.utils;

public class InputValidator {

    public static void checkNotNull(Object... objectsToCheck) {
        if (objectsToCheck == null) {
            throw new IllegalArgumentException("provided array must not be null");
        }
        for (Object o : objectsToCheck) {
            if (o == null) {
                throw new IllegalArgumentException("null not allowed!");
            }
        }
    }

    public static void checkNotNegative(Integer number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative number not allowed");
        }
    }

    public static void checkNotNegative(Double number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative number not allowed");
        }
    }

}
