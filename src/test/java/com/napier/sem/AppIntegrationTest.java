package com.napier.sem;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.datalayer.MySqlDataLayer;
import com.napier.sem.service.AppServices;
import com.napier.sem.service.DefaultAppServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testCapitalCityReports() {
        appServices.getAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest();
        appServices.getAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe");
        appServices.getAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("North America");
        appServices.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 5);
        appServices.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("North America", 4);
        appServices.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(3);
    }

    @Test
    public void testCountryReports(){
        appServices.getAllCountriesOrderedByLargestPopulationToSmallest();
        appServices.getAllCountriesInAContinentOrderedByLargestPopulationToSmallest("Asia");
        appServices.getAllCountriesInARegionOrderedByLargestPopulationToSmallest("North America");
        appServices.getCountriesFromWorldOrderedByLargestPopulationToSmallest(10);
        appServices.getCountriesFromContinentOrderedByLargestPopulationToSmallest("Asia",5);
        appServices.getCountriesFromRegionOrderedByLargestPopulationToSmallest("North America", 5);
    }

    @Test
    public void testPopulationReports(){
        appServices.getPopulationOfPeopleInEachContinent();
        appServices.getPopulationOfPeopleInEachRegion();
        appServices.getPopulationOfPeopleInEachCountry();
    }
    @Test
    public void testSimplePopulationReports(){
        appServices.getThePopulationOfTheWorld();
        appServices.getThePopulationOfAContinent("Asia");
        appServices.getThePopulationOfARegion("North America");
        appServices.getThePopulationOfACountry("Germany");
        appServices.getThePopulationOfADistrict("Kabol");
        appServices.getThePopulationOfACity("Edinburgh");

    }

    @Test
    public void testLanguageReport(){

        appServices.getLanguageReport(new String[]{ "German", "English" });
    }

}
