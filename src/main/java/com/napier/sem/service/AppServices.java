package com.napier.sem.service;

import com.napier.sem.reports.CapitalCityReport;
import com.napier.sem.reports.CityReport;
import com.napier.sem.reports.CountryReport;

import java.sql.SQLException;
import java.util.List;

/**
 * The <code>AppServices</code> interface defines all types of reports that can be generated.
 */
public interface AppServices {

    /**
     * Generates a list of reports with all the countries in the world organised by largest population to smallest.
     * @return a ordered list containing all country reports
     * @throws SQLException if report could not be generated
     */
    List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest() throws SQLException;

    /**
     * Generates a list of reports with all cities in the world organized by largest population to smallest
     * @return a ordered list containing all cities in the world
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest() throws SQLException;

    /**
     * Generates a list of reports with all cities in a continent organized by largest population to smallest
     * @param continentsql is the input for a specific continent
     * @return a ordered list containing all cities in the selected continent
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continentsql ) throws SQLException;

    /**
     * Generates a list of reports with all cities in a region organized by largest population to smallest
     * @param regionsql is the input for a specific region
     * @return a ordered list containing all cities in the selected region
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws SQLException;

    /**
     * Generates a list of reports with all cities in a country organized by largest population to smallest
     * @param countrysql is the input for a specific country
     * @return a ordered list containing all cities in the selected country
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String countrysql) throws SQLException;

    /**
     * Generates a list of reports with all cities in a district organized by largest population to smallest
     * @param distrcitsql is the input for a specific district
     * @return a ordered list containing all cities in the selected district
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getAllCitiesFromDistrictOrderedByLargestPopulationToSmallest(String distrcitsql) throws SQLException;

    /**
     * Generates a list of reports with N countries in the world organized by largest population to smallest
     * @param limitsql is the input for the amount of countries which should be presented
     * @return a ordered list containing N countries in the world
     * @throws SQLException if report could not be generated
     */
    List<CountryReport> getNCountriesFromWorldOrderedByLargestPopulationToSmallestNisSelectedByUser(int limitsql) throws SQLException;

    /**
     * Generates a list of reports with N countries in a continent organized by largest population to smallest
     * @param continentsql is the input for a specific continent
     * @param limitsql  is the input for the amount of countries in the specific continent which should be presented
     * @return a ordered list containing N countries of the selected continent
     * @throws SQLException if report could not be generated
     */
    List<CountryReport> getNCountriesFromContinentOrderedByLargestPopulationToSmallestNisSelectedByUser(String continentsql, int limitsql) throws SQLException;

    /**
     * Generates a list of reports with N countries in a region organized by largest population to smallest
     * @param regionsql is the input to specify the region
     * @param limitsql  is the input for the amount of countries in the specific region which should be presented
     * @return a ordered list containing N countries of the selected region
     * @throws SQLException if report could not be generated
     */
    List<CountryReport> getNCountriesFromRegionOrderedByLargestPopulationToSmallestNisSelectedByUser(String regionsql, int limitsql) throws SQLException;

    /**
     * Generates a list of reports with N Capital Cities in the world organized by largest population to smallest
     * @param limitsql is the input for the amount of Capital Cities which should be presented
     * @return a ordered list containing N Capital Cities of the world
     * @throws SQLException if report could not be generated
     */
    List<CapitalCityReport> getNCapitalCitiesByLargestPopulationToSmallestNisSelectedByUser(int limitsql) throws SQLException;

    /**
     * Generates a list of reports with N Cities in the world organized by largest population to smallest
     * @param limitsql is the input for the amount of Cities which should be presented
     * @return a ordered list containing N Cities of the world
     * @throws SQLException if report could not be generated
     */
    List<CityReport> getNCitiesByLargestPopulationToSmallestWhereNisSelectedByUser(int limitsql) throws SQLException;

    List<CapitalCityReport> getAllCapitalCitiesfromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws  SQLException;
}
