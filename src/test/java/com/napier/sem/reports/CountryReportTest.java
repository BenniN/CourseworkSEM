package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountryReportTest {

    @Test
    public void testCountryReport() {
        CountryReport report = new CountryReport(
                "GER",
                "Germany",
                "Europe",
                "Western-Europe",
                80000000,
                "Berlin");

        assertEquals("GER", report.getCode());
        assertEquals("Germany", report.getName());
        assertEquals("Europe", report.getContinent());
        assertEquals("Western-Europe", report.getRegion());
        assertEquals(80000000, report.getPopulation());
        assertEquals("Berlin", report.getCapital());
    }

    @Test
    public void testCountryReportCodeLength() {
        assertThrows(IllegalArgumentException.class,
                () -> new CountryReport("ASDE", "null", "null", "null", 0, "null"));
        assertThrows(IllegalArgumentException.class,
                () -> new CountryReport("DE", "null", "null", "null", 0, "null"));
    }

}
