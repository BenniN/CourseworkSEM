package com.napier.sem.datalayer;

import com.napier.sem.reports.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlDataLayerTest {

    private static MySqlDataLayer dataLayer;

    @BeforeAll
    public static void init() {
        dataLayer = Mockito.spy(new MySqlDataLayer("host", "user", "password"));
    }

    @Test
    public void testProduceSimplePopulationReport() throws SQLException {
        ResultSet result = Mockito.mock(ResultSet.class);
        Mockito.when(result.next()).thenReturn(true).thenReturn(false);
        Mockito.when(result.getString("name")).thenReturn("Edinburgh");
        Mockito.when(result.getInt("population")).thenReturn(12345);
        Mockito.doReturn(result).when(dataLayer).executeSQLAndReturnResultSet(Mockito.any(), Mockito.anyInt());

        SimplePopulationReport report = dataLayer.produceSimplePopulationReport(null);
        assertEquals("Edinburgh", report.getName());
        assertEquals(12345, report.getPopulation());
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
        Mockito.when(resultSet.getInt("population_total")).thenReturn(10);
        Mockito.when(resultSet.getInt("population_cities")).thenReturn(4);
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
