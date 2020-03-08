package com.napier.sem;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.datalayer.MySqlDataLayer;
import com.napier.sem.service.AppServices;
import com.napier.sem.service.DefaultAppServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    private static DataLayer dataLayer;
    private static AppServices appServices;

    @BeforeAll
    public static void init() {
        // set up database connection
        dataLayer = new MySqlDataLayer("localhost:33060", "root", "example");
        assertTrue(dataLayer.initialize());

        // set up business layer
        appServices = new DefaultAppServices(dataLayer);
    }

    @Test
    public void testMe() {
        dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest("Germany", 5);
    }

    @Test
    public void testAllCountriesOrderedByLargestPopulationToSmallest() {
        appServices.getAllCountriesOrderedByLargestPopulationToSmallest();
    }

}
