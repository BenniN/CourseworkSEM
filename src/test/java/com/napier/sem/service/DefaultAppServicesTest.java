package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.exceptions.ServiceException;
import com.napier.sem.reports.CapitalCityReport;
import com.napier.sem.reports.CityReport;
import com.napier.sem.reports.CountryReport;
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

    @BeforeEach
    public void init() {
        this.dataLayer = Mockito.mock(DataLayer.class);
        this.appServices = new DefaultAppServices(dataLayer);

        appServices.continentValidator = Mockito.spy(appServices.continentValidator);
        appServices.regionValidator = Mockito.spy(appServices.regionValidator);
        appServices.limitValidator = Mockito.spy(appServices.limitValidator);

        // spy validators
        this.continentValidator = appServices.continentValidator;
        this.regionValidator = appServices.regionValidator;
        this.limitValidator = appServices.limitValidator;
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
        Mockito.when(dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT));
        Mockito.verify(dataLayer).getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
        Mockito.verify(limitValidator).validate(DataLayer.NO_LIMIT);
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
    }

    @Test
    public void testGetCitiesInADistrictOrganisedByLargestPopulationToSmallest(){
        List<CityReport> reports = getSampleCityReports();
        Mockito.when(dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1)).thenReturn(reports);
        assertEquals(reports,appServices.getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1));
        Mockito.verify(dataLayer).getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland",1);
        Mockito.verify(limitValidator).validate(1);
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
}
