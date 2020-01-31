package com.napier.sem.reports;

import org.junit.Test;
import static org.junit.Assert.*;

public class PopulationReportTests {

    @Test
    public void testPercentageComputation() {
        PopulationReport report = new PopulationReport("test", 150, 100);
        assertEquals("66.7%", report.getPopulationCitiesPercentage());
        assertEquals("33.3%", report.getPopulationNoCitesPercentage());
    }

    @Test
    public void testPopulationComputation() {
        PopulationReport report = new PopulationReport("test", 350200, 280000);
        assertEquals(Integer.valueOf(280000), report.getPopulationCities());
        assertEquals(Integer.valueOf(70200), report.getPopulationNoCities());
    }

}
