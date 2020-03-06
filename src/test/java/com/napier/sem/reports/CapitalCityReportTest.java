package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityReportTest {

    @Test
    public void testCapitalCitySuccessfulObjectCreation() {
        CapitalCityReport report = new CapitalCityReport("Berlin", "Germany", 1234567);
        assertEquals("Berlin", report.getName());
        assertEquals("Germany", report.getCountry());
        assertEquals(1234567, report.getPopulation());
    }

    @Test
    public void testCapitalCityThrowExceptionForNullInput() {
        assertThrows(IllegalArgumentException.class, () -> new CapitalCityReport("Berlin", null, null));
        assertThrows(IllegalArgumentException.class, () -> new CapitalCityReport(null, "Germany", null));
        assertThrows(IllegalArgumentException.class, () -> new CapitalCityReport(null, "Germany", 12345));
    }

    @Test
    public void testCapitalCityNegativePopulation() {
        assertThrows(IllegalArgumentException.class, () ->
                new CapitalCityReport("Berlin", "Germany", -500));
    }

}
