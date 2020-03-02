package com.napier.sem;

import com.napier.sem.datalayer.MySqlDataLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppIntegrationTest {

    private static MySqlDataLayer dataLayer;

    @BeforeAll
    public static void init() {
        dataLayer = new MySqlDataLayer("localhost:33060", "root", "example");
        dataLayer.initialize();
    }

    @Test
    public void testMe() {
        dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest("Germany", 5);
    }

}
