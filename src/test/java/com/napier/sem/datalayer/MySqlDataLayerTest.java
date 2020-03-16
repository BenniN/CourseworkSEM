package com.napier.sem.datalayer;

import com.napier.sem.reports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySqlDataLayerTest {

    private MySqlDataLayer dataLayer;


    @BeforeEach
    public void setUp() {
        dataLayer = Mockito.spy(new MySqlDataLayer("host", "user", "password"));
    }

    @Test
    public void testGetCountriesInTheWorldOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCountryReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetCountriesInAContinentOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest("Europe", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCountryReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetCountriesInARegionOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest("British Islands",DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCountryReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest() {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCapitalCityReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest() {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest("Europe", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCapitalCityReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest() {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCapitalCityReport(Mockito.any(), Mockito.anyInt());
    }


    @Test
    public void testGetCitiesInTheWorldOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(),Mockito.anyInt());
        dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(),Mockito.anyInt());
        Mockito.verify(dataLayer).produceCityReport(Mockito.any(),Mockito.anyInt());
    }
    @Test
    public void testGetCitiesInARegionOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCitiesInARegionOrganisedByLargestPopulationToSmallest("British Islands", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCityReport(Mockito.any(), Mockito.anyInt());

    }

    @Test
    public void testGetCitiesInAContinentOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCitiesInAContinentOrganisedByLargestPopulationToSmallest("Asia", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCityReport(Mockito.any(), Mockito.anyInt());

    }

    @Test
    public void testGetCitiesInACountryOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest("United Kingdom", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCityReport(Mockito.any(), Mockito.anyInt());

    }

    @Test
    public void testGetCitiesInADistrictOrganisedByLargestPopulationToSmallest(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest("Scotland", DataLayer.NO_LIMIT);
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceCityReport(Mockito.any(), Mockito.anyInt());

    }

    @Test
    public void testGetThePopulationOfTheWorld(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfTheWorld();
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetThePopulationOfAContinent(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfAContinent("Asia");
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceSimplePopulationReport(Mockito.any());
    }

    @Test
    public void testGetThePopulationOfARegion(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfARegion("North America");
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceSimplePopulationReport(Mockito.any());
    }

    @Test
    public void testGetThePopulationOfACountry(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfACountry("Germany");
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceSimplePopulationReport(Mockito.any());
    }

    @Test
    public void testGetThePopulationOfADistrict(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfACountry("Kabol");
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceSimplePopulationReport(Mockito.any());
    }

    @Test
    public void testGetThePopulationOfACity(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getThePopulationOfACity("Edinburgh");
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceSimplePopulationReport(Mockito.any());
    }

    @Test
    public void testGetPopulationOfPeopleInEachContinent(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getPopulationOfPeopleInEachContinent();
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).producePopulationReport(Mockito.any(), Mockito.anyInt());
    }
    @Test
    public void testGetPopulationOfPeopleInEachRegion(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getPopulationOfPeopleInEachRegion();
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).producePopulationReport(Mockito.any(), Mockito.anyInt());
    }
    @Test
    public void testGetPopulationOfPeopleInEachCountry(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getPopulationOfPeopleInEachCountry();
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).producePopulationReport(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testGetLanguageReport(){
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        dataLayer.getLanguageReport(new String[]{ "German", "English" });
        Mockito.verify(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());
        Mockito.verify(dataLayer).produceLanguageReport(Mockito.any());
    }







    @Test
    public void testProduceSimplePopulationReport() throws SQLException {
        ResultSet result = Mockito.mock(ResultSet.class);
        Mockito.when(result.next()).thenReturn(true).thenReturn(false);
        Mockito.when(result.getString("name")).thenReturn("Edinburgh");
        Mockito.when(result.getLong("population")).thenReturn(12345L);
        Mockito.doReturn(result).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        SimplePopulationReport report = dataLayer.produceSimplePopulationReport(null);
        assertEquals("Edinburgh", report.getName());
        assertEquals(12345L, report.getPopulation());
    }

    @Test
    public void testProduceCityReport() throws SQLException {
        ResultSet result = Mockito.mock(ResultSet.class);
        Mockito.when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(result.getString("name")).thenReturn("Edinburgh").thenReturn("Munich");
        Mockito.when(result.getInt("population")).thenReturn(112233).thenReturn(445566);
        Mockito.when(result.getString("district")).thenReturn("Scotland").thenReturn("Bavaria");
        Mockito.when(result.getString("country")).thenReturn("Great Britain").thenReturn("Germany");
        Mockito.doReturn(result).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        List<CityReport> cityReport = dataLayer.produceCityReport(null, DataLayer.NO_LIMIT);
        assertEquals(2, cityReport.size());
        CityReport report1 = cityReport.get(0);
        CityReport report2 = cityReport.get(1);
        assertEquals("Edinburgh", report1.getName());
        assertEquals("Munich", report2.getName());
        assertEquals(112233, report1.getPopulation());
        assertEquals(445566, report2.getPopulation());
        assertEquals("Scotland", report1.getDistrict());
        assertEquals("Bavaria", report2.getDistrict());
        assertEquals("Great Britain", report1.getCountry());
        assertEquals("Germany", report2.getCountry());
    }

    @Test
    public void testProduceCapitalCityReport() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("name")).thenReturn("Edinburgh");
        Mockito.when(resultSet.getString("country")).thenReturn("Great Britain");
        Mockito.when(resultSet.getInt("population")).thenReturn(123456);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        List<CapitalCityReport> reports = dataLayer.produceCapitalCityReport(null, DataLayer.NO_LIMIT);
        assertEquals(1, reports.size());
        CapitalCityReport report = reports.get(0);
        assertEquals("Edinburgh", report.getName());
        assertEquals("Great Britain", report.getCountry());
        assertEquals(123456, report.getPopulation());
    }

    @Test
    public void testProduceCountryReport() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("code")).thenReturn("GBR");
        Mockito.when(resultSet.getString("name")).thenReturn("United Kingdom");
        Mockito.when(resultSet.getString("continent")).thenReturn("Europe");
        Mockito.when(resultSet.getInt("population")).thenReturn(1234567);
        Mockito.when(resultSet.getString("region")).thenReturn("British Islands");
        Mockito.when(resultSet.getString("capital")).thenReturn("London");
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        List<CountryReport> reports = dataLayer.produceCountryReport(null, DataLayer.NO_LIMIT);
        assertEquals(1, reports.size());
        CountryReport report = reports.get(0);
        assertEquals("GBR", report.getCode());
        assertEquals("United Kingdom", report.getName());
        assertEquals("Europe", report.getContinent());
        assertEquals(1234567, report.getPopulation());
        assertEquals("British Islands", report.getRegion());
        assertEquals("London", report.getCapital());
    }

    @Test
    public void testProduceLanguageReport() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("language")).thenReturn("English");
        Mockito.when(resultSet.getInt("speakers")).thenReturn(1234);
        Mockito.when(resultSet.getDouble("percentage")).thenReturn(3.4);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        List<LanguageReport> languageReports = dataLayer.produceLanguageReport(null);
        assertEquals(1, languageReports.size());
        LanguageReport report = languageReports.get(0);
        assertEquals("English", report.getLanguage());
        assertEquals(1234, report.getSpeakers());
        assertEquals(3.4, report.getPercentage());
    }

    @Test
    public void testProducePopulationReport() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString("name")).thenReturn("Germany");
        Mockito.when(resultSet.getLong("population_total")).thenReturn(10L);
        Mockito.when(resultSet.getLong("population_cities")).thenReturn(4L);
        Mockito.doReturn(resultSet).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        List<PopulationReport> reports = dataLayer.producePopulationReport(null, DataLayer.NO_LIMIT);
        assertEquals(1, reports.size());
        PopulationReport report = reports.get(0);
        assertEquals("Germany", report.getName());
        assertEquals(10, report.getTotalPopulation());
        assertEquals(4, report.getPopulationCities());
        assertEquals(6, report.getPopulationNoCities());
    }

    @Test
    public void testBuildLanguageSqlList() {
        String[] languages = new String[] {"German", "English", "Spain"};
        assertEquals("'German', 'English', 'Spain'", dataLayer.buildLanguageSqlList(languages));

        String[] singleLanguage = new String[] {"English"};
        assertEquals("'English'", dataLayer.buildLanguageSqlList(singleLanguage));
    }

}
