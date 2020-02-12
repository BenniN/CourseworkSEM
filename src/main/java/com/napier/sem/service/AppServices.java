package com.napier.sem.service;

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
    //get all Cities from ...
    List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest() throws SQLException;
    List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continentsql ) throws SQLException;
    List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws SQLException;
    List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String countrysql) throws SQLException;
    List<CityReport> getAllCitiesFromDistrictOrderedByLargestPopulationToSmallest(String distrcitsql) throws SQLException;
    // get all countries from ...
    List<CountryReport> getNCountriesFromWorldOrderedByLargestPopulationToSmallestNisSelectedByUser(int limitsql) throws SQLException;
}
