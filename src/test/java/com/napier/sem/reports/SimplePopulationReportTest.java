package com.napier.sem.reports;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimplePopulationReportTest {

    @Test
    public void testSuccessfulObjectCreation() {
        SimplePopulationReport report = new SimplePopulationReport("Bavaria", 1400000L);
        assertEquals("Bavaria", report.getName());
        assertEquals(1400000, report.getPopulation());
    }

}
