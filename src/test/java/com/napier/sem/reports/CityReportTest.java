package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityReportTest {

    @Test
    public void testSuccessfulObjectCreation() {
        CityReport report = new CityReport("Munich", "Germany", "Bavaria", 14000000);
        assertEquals("Munich", report.getName());
        assertEquals("Germany", report.getCountry());
        assertEquals("Bavaria", report.getDistrict());
        assertEquals(14000000, report.getPopulation());
    }

    @Test
    public void testIfInvalidInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new CityReport(null, null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new CityReport("Munich", "Germany", "Bavaria", -1));
    }

}
