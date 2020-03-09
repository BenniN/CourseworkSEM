package com.napier.sem.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void testGetFileAsString() {
        String fileContent = Utils.getFileAsString("/test.txt");
        assertEquals("This file contains some text.\nAnd another line", fileContent);
        assertNull(Utils.getFileAsString("/file_that_is_not_existent.txt"));
    }

}
