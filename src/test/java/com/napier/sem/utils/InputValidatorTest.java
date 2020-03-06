package com.napier.sem.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    public void testCheckNotNull() {
        InputValidator.checkNotNull("hello", 5, 'A');

        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.checkNotNull(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.checkNotNull("hello", 5, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            InputValidator.checkNotNull(null, null, "test");
        });
    }

    @Test
    public void testCheckNotNegativeForInteger() {
        // check allowed numbers
        InputValidator.checkNotNegative(0);
        InputValidator.checkNotNegative(1);

        // check if exception is thrown for negative numbers
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.checkNotNegative(-1));
    }

    @Test
    public void testCheckNotNegativeForDouble() {
        // check allowed numbers
        InputValidator.checkNotNegative(0.0);
        InputValidator.checkNotNegative(0.1);

        // check if exception is thrown for negative numbers
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.checkNotNegative(-0.1));
    }



}
