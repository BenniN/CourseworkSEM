package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageReportTest {

    @Test
    public void testSuccessfulObjectCreation() {
        LanguageReport report = new LanguageReport("German", 123456, 4.3);
        assertEquals("German", report.getLanguage());
        assertEquals(123456, report.getSpeakers());
        assertEquals(4.3, report.getPercentage());
    }

    @Test
    public void testIllegalArgumentsForObjectCreation() {
        assertThrows(IllegalArgumentException.class,
                () -> new LanguageReport(null, 1234, 5.0));

        assertThrows(IllegalArgumentException.class,
                () -> new LanguageReport("German", 12345, -2.3));
    }

}
