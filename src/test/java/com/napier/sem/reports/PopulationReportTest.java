package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PopulationReportTest {

    @Test
    public void testSuccessfulObjectCreation() {
        PopulationReport report = new PopulationReport("Uganda", 100, 20);
        assertEquals("Uganda", report.getName());
        assertEquals(100, report.getTotalPopulation());
        assertEquals(20, report.getPopulationCities());
        assertEquals(80, report.getPopulationNoCities());
        assertEquals("20.0%", report.getPopulationCitiesPercentage());
        assertEquals("80.0%", report.getPopulationNoCitesPercentage());
    }

    @Test
    public void testIfInvalidInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PopulationReport(null, 2, 4));
        assertThrows(IllegalArgumentException.class, () -> new PopulationReport("Germany", 123, -4));
        assertThrows(IllegalArgumentException.class, () -> new PopulationReport("Germany", 100, 200));
    }

}
