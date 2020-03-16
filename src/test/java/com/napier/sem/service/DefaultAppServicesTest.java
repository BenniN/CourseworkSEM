package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.exceptions.ServiceException;
import com.napier.sem.reports.*;
import com.napier.sem.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAppServicesTest {

    private DataLayer dataLayer;
    private DefaultAppServices appServices;

    private Validator<String> continentValidator;
    private Validator<String> regionValidator;
    private Validator<Integer> limitValidator;
    private Validator<String> countryValidator;
    private Validator<String> districtValidator;

    @BeforeEach
    public void init() {
        this.dataLayer = Mockito.mock(DataLayer.class);
        this.appServices = new DefaultAppServices(dataLayer);

        appServices.continentValidator = Mockito.spy(appServices.continentValidator);
        appServices.regionValidator = Mockito.spy(appServices.regionValidator);
        appServices.limitValidator = Mockito.spy(appServices.limitValidator);
        appServices.countryValidator = Mockito.spy(appServices.countryValidator);
        appServices.districtValidator = Mockito.spy(appServices.districtValidator);

        // spy validators
        this.continentValidator = appServices.continentValidator;
        this.regionValidator = appServices.regionValidator;
        this.limitValidator = appServices.limitValidator;
        this.countryValidator = appServices.countryValidator;
        this.districtValidator = appServices.districtValidator;

    }

    @Test
    public void testAllCountriesOrderedByLargestPopulationToSmallest() {
        Mockito.when(dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT)).thenReturn(Collections.emptyList());
        appServices.getAllCountriesOrderedByLargestPopulationToSmallest();
        Mockito.verify(dataLayer).getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Test
    public void testAllCountriesInAContinentOrderedByLargestPopulationToSmallest(){
        List<CountryReport> reports = getSampleCountryReports();
        Mockito.when(dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest("Europe",DataLayer.NO_LIMIT)).thenReturn(reports);
        List<CountryReport> results =appServices.getAllCountriesInAContinentOrderedByLargestPopulationToSmallest("Europe");
        assertEquals(reports.size(),results.size());
        assertEquals(reports.get(0),results.get(0));
        Mockito.verify(dataLayer).getCountriesInAContinentOrganisedByLargestPopulationToSmallest("Europe",DataLayer.NO_LIMIT);
        Mockito.verify(continentValidator).validate("Europe");
    }

    @Test
    public void testAllCountriesInARegionOrderedByLargestPopulationToSmallest(){
        List<CountryReport> reports = getSampleCountryReports();
        Mockito.when(dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT)).thenReturn(reports);
        assertEquals(reports, appServices.getAllCountriesInARegionOrderedByLargestPopulationToSmallest("British Islands"));
        Mockito.verify(dataLayer).getCountriesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT);
        Mockito.verify(regionValidator).validate("British Islands");
    }

    @Test
    public void testCountriesFromWorldOrderedByLargestPopulationToSmallest(){
        List<CountryReport> reports = getSampleCountryReports();
        Mockito.when(dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(1)).thenReturn(reports);
        assertEquals(reports, appServices.getCountriesFromWorldOrderedByLargestPopulationToSmallest(1));
        Mockito.verify(dataLayer).getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(1);
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testCountriesFromContinentOrderedByLargestPopulationToSmallest(){
        List<CountryReport> reports = getSampleCountryReports();
        Mockito.when(dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 1)).thenReturn(reports);
        assertEquals(reports, appServices.getCountriesFromContinentOrderedByLargestPopulationToSmallest("Europe", 1));
        Mockito.verify(dataLayer).getCountriesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 1);
        Mockito.verify(continentValidator).validate("Europe");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testCountriesFromRegionOrderedByLargestPopulationToSmallest(){
        List<CountryReport> reports = getSampleCountryReports();
        Mockito.when(dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest("British Islands", 1)).thenReturn(reports);
        assertEquals(reports, appServices.getCountriesFromRegionOrderedByLargestPopulationToSmallest("British Islands", 1));
        Mockito.verify(dataLayer).getCountriesInARegionOrganisedByLargestPopulationToSmallest("British Islands", 1);
        Mockito.verify(regionValidator).validate("British Islands");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest() {
        Mockito.when(dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT)).thenReturn(Collections.emptyList());
        appServices.getAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest();
        Mockito.verify(dataLayer).getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Test
    public void testGetAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest() {
        List<CapitalCityReport> reports = getSampleCapitalCityReports();
        Mockito.when(dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", DataLayer.NO_LIMIT)).
                thenReturn(reports);
        List<CapitalCityReport> results = appServices.getAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe");
        assertEquals(reports.size(), results.size());
        assertEquals(reports.get(0), results.get(0));
        Mockito.verify(dataLayer).getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", DataLayer.NO_LIMIT);
        Mockito.verify(continentValidator).validate("Europe");
    }

    @Test
    public void testGetAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest() {
        List<CapitalCityReport> reports = getSampleCapitalCityReports();
        Mockito.when(dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT)).thenReturn(reports);
        assertEquals(reports, appServices.getAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands"));
        Mockito.verify(dataLayer).getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT);
        Mockito.verify(regionValidator).validate("British Islands");
    }

    @Test
    public void testGetCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest() {
        List<CapitalCityReport> reports = getSampleCapitalCityReports();
        Mockito.when(dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1)).thenReturn(reports);
        assertEquals(reports, appServices.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1));
        Mockito.verify(dataLayer).getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1);
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest() {
        List<CapitalCityReport> reports = getSampleCapitalCityReports();
        Mockito.when(dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 1)).thenReturn(reports);
        assertEquals(reports, appServices.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 1));
        Mockito.verify(dataLayer).getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", 1);
        Mockito.verify(continentValidator).validate("Europe");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest() {
        List<CapitalCityReport> reports = getSampleCapitalCityReports();
        Mockito.when(dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", 1)).thenReturn(reports);
        assertEquals(reports, appServices.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", 1));
        Mockito.verify(dataLayer).getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", 1);
        Mockito.verify(regionValidator).validate("British Islands");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCitiesInTheWorldOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1));
        Mockito.verify(dataLayer).getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(1);
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCitiesInARegionOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands",1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands",1));
        Mockito.verify(dataLayer).getCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands",1);
        Mockito.verify(regionValidator).validate("British Islands");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCitiesInAContinentOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInAContinentOrganisedByLargestPopulationToSmallest("Asia",1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInAContinentOrganisedByLargestPopulationToSmallest("Asia",1));
        Mockito.verify(dataLayer).getCitiesInAContinentOrganisedByLargestPopulationToSmallest("Asia",1);
        Mockito.verify(continentValidator).validate("Asia");
        Mockito.verify(limitValidator).validate(1);
    }

    @Test
    public void testGetCitiesInACountryOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest("United Kingdom",1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInACountryOrganisedByLargestPopulationToSmallest("United Kingdom",1));
        Mockito.verify(dataLayer).getCitiesInACountryOrganisedByLargestPopulationToSmallest("United Kingdom",1);
        Mockito.verify(limitValidator).validate(1);
        Mockito.verify(countryValidator).validate("United Kingdom");
    }

    @Test
    public void testGetCitiesInADistrictOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1));
        Mockito.verify(dataLayer).getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1);
        Mockito.verify(limitValidator).validate(1);
        Mockito.verify(districtValidator).validate("Scotland");
    }

    @Test
    public void testGetThePopulationOfTheWorld(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfTheWorld());
        assertEquals(reports,appServices.getThePopulationOfTheWorld());
        Mockito.verify(dataLayer).getThePopulationOfTheWorld();
        Mockito.verify(districtValidator).validate("Scotland");
    }
    @Test
    public void testGetThePopulationOfAContinent(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfAContinent("Asia"));
        assertEquals(reports,appServices.getThePopulationOfAContinent("Asia"));
        Mockito.verify(dataLayer).getThePopulationOfAContinent("Asia");
        Mockito.verify(continentValidator).validate("Asia");
    }
    @Test
    public void testGetThePopulationOfARegion(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfARegion("North America"));
        assertEquals(reports,appServices.getThePopulationOfARegion("North America"));
        Mockito.verify(dataLayer).getThePopulationOfARegion("North America");
        Mockito.verify(regionValidator).validate("North America");
    }

    @Test
    public void testGetThePopulationOfACountry(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfACountry("Germany"));
        assertEquals(reports,appServices.getThePopulationOfACountry("Germany"));
        Mockito.verify(dataLayer).getThePopulationOfACountry("Germany");
        Mockito.verify(countryValidator).validate("Germany");
    }

    @Test
    public void testGetThePopulationOfADistrict(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfADistrict("Kabul"));
        assertEquals(reports,appServices.getThePopulationOfADistrict("Kabul"));
        Mockito.verify(dataLayer).getThePopulationOfADistrict("Kabul");
        Mockito.verify(districtValidator).validate("Kabul");
    }


    @Test
    public void testGetThePopulationOfACity(){
        List<SimplePopulationReport> reports = getSampleSimplePopulationReports();
        Mockito.when(dataLayer.getThePopulationOfACity("Edinburgh"));
        assertEquals(reports,appServices.getThePopulationOfACity("Edinburgh"));
        Mockito.verify(dataLayer).getThePopulationOfACity("Edinburgh");
        // couldn't find cityValidator //
    }

    @Test
    public void testGetPopulationOfPeopleInEachCountry(){
        List<PopulationReport> reports = getSamplePopulationReports();
        Mockito.when(dataLayer.getPopulationOfPeopleInEachCountry());
        assertEquals(reports,appServices.getPopulationOfPeopleInEachCountry());
        Mockito.verify(dataLayer).getPopulationOfPeopleInEachCountry();
        Mockito.verify(countryValidator).validate("Germany");
    }

    @Test
    public void testGetPopulationOfPeopleInEachRegion(){
        List<PopulationReport> reports = getSamplePopulationReports();
        Mockito.when(dataLayer.getPopulationOfPeopleInEachRegion());
        assertEquals(reports,appServices.getPopulationOfPeopleInEachRegion());
        Mockito.verify(dataLayer).getPopulationOfPeopleInEachRegion();
        Mockito.verify(regionValidator).validate("North America");
    }

    @Test
    public void testGetPopulationOfPeopleInEachContinent(){
        List<PopulationReport> reports = getSamplePopulationReports();
        Mockito.when(dataLayer.getPopulationOfPeopleInEachContinent());
        assertEquals(reports,appServices.getPopulationOfPeopleInEachContinent());
        Mockito.verify(dataLayer).getPopulationOfPeopleInEachContinent();
        Mockito.verify(continentValidator).validate("Asia");
    }

    @Test
    public void testtestGetLanguageReport(){
        List<LanguageReport> reports = getSampleLanguageReport();
        Mockito.when(dataLayer.getLanguageReport(new String[]{ "German", "English" }));
        assertEquals(reports,appServices.getLanguageReport(new String[]{ "German", "English" }));
        Mockito.verify(dataLayer).getLanguageReport(new String[]{ "German", "English" });
    }







    @Test
    public void testContinentValidator() {
        continentValidator.validate("Europe");
        continentValidator.validate("Asia");
        continentValidator.validate("North America");
        continentValidator.validate("Africa");
        continentValidator.validate("Oceania");
        continentValidator.validate("Antarctica");
        continentValidator.validate("South America");

        assertThrows(ServiceException.class, () -> continentValidator.validate(null));
        assertThrows(ServiceException.class, () -> continentValidator.validate(""));
        assertThrows(ServiceException.class, () -> continentValidator.validate("Italy"));
    }

    @Test
    public void testRegionValidator() {
        regionValidator.validate("British Islands");
        regionValidator.validate("Central Africa");

        assertThrows(ServiceException.class, () -> regionValidator.validate(null));
        assertThrows(ServiceException.class, () -> regionValidator.validate(""));
        assertThrows(ServiceException.class, () -> regionValidator.validate("region?"));
        assertThrows(ServiceException.class, () -> regionValidator.validate("--region"));
    }

    @Test
    public void testLimitValidator() {
        limitValidator.validate(1);
        limitValidator.validate(2);

        assertThrows(ServiceException.class, () -> limitValidator.validate(null));
        assertThrows(ServiceException.class, () -> limitValidator.validate(0));
        assertThrows(ServiceException.class, () -> limitValidator.validate(-1));
        assertThrows(ServiceException.class, () -> limitValidator.validate(-2));
    }


    private List<CapitalCityReport> getSampleCapitalCityReports() {
        return List.of(new CapitalCityReport("Berlin", "Germany", 12345));
    }

   private List<CountryReport> getSampleCountryReports(){
        return List.of(new CountryReport("ABC","Germany","Europe","SomeRegion",1234567,"TestCapital"));
    }

    private  List<CityReport> getSampleCityReports(){
        return List.of(new CityReport("Belfast","Northern Ireland","North Ireland",287500));
    }

    private List<SimplePopulationReport> getSampleSimplePopulationReports(){
        return List.of(new SimplePopulationReport("Kabul", 1780000));
    }

    private List<PopulationReport> getSamplePopulationReports(){
        return List.of(new PopulationReport("Germany", 82164700, 26245483));
    }

    private List<LanguageReport> getSampleLanguageReport(){
        return List.of(new LanguageReport("German", 82164700,100));
    }
}
