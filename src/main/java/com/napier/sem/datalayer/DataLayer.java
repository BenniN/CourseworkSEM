package com.napier.sem.datalayer;

import com.napier.sem.reports.*;

import java.util.List;

/**
 * The <code>DataLayer</code> interface provides methods for communication
 * and interaction with a system that provides data for the application.<br/>
 * <p>
 * E.g. it provides a <code>connect()</code> method for establishing a connection
 * such as a <code>disconnect()</code> method for closing the connection.<br/>
 * <p>
 * For setting up native sql queries on the host system, use the <code>executeNativeQuery()</code>
 * method.
 */
public interface DataLayer {

    int NO_LIMIT = -1;

    /**
     * Establishes the connection to a host system.
     * The implementing class needs to care about how the connection is established.
     * @return true if setup successfully, false otherwise
     */
    boolean initialize();

    /**
     * Produces a list of country reports for all countries in the world organised by largest population to smallest.
     * @param limit the max amount of reports returned
     * @return a list of country reports
     */
    List<CountryReport> getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(int limit);

    /**
     * Produces a list of country reports for countries in a continent organised by largest population to smallest.
     * @param continent the continent to produce the country reports from.
     * @param limit the max amount of reports returned
     * @return a list of country reports for that continent
     */
    List<CountryReport> getCountriesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Produces a list of country reports for a region organised by largest population to smallest.
     * @param region the region to produce the country reports from
     * @param limit the max amount of reports returned
     * @return a list of countries for that region
     */
    List<CountryReport> getCountriesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit);

    /**
     * Produces a list of city reports for all cities in the world organised by largest population to smallest.
     * @param limit the max amount of reports returned
     * @return a list of city reports
     */
    List<CityReport> getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit);

    /**
     * Produces a list of city reports for all cities in a continent organised by largest population to smallest.
     * @param continent the continent to produce the city reports from
     * @param limit the max amount of reports returned
     * @return a list of city reports for that continent
     */
    List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Produces a list of city reports for cities in a region organised by largest population to smallest.
     * @param region the region to produce the reports from
     * @param limit the max amount of reports returned
     * @return a list of city reports for that region
     */
    List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit);

    /**
     * Produces a list of city reports for cities in a country organised by largest population to smallest.
     * @param country the country to produce the city reports from.
     * @param limit the max amount of reports returned
     * @return a list of city reports for that country
     */
    List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit);

    /**
     * Produces a list of city reports for cities in a district organised by largest population to smallest.
     * @param district the district to generate the reports from.
     * @param limit the max amount of reports returned
     * @return a list of city reports for that district
     */
    List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit);

    /**
     * Produces a list of capital city reports for all capital cities in the world organised by largest population to smallest.
     * @param limit the max amount of reports returned
     * @return a list of capital city reports
     */
    List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit);

    /**
     * Produces a list of capital city reports for capital cities in a continent organised by largest population to smallest.
     * @param continent the continent to produce the reports from
     * @param limit the max amount of reports returned
     * @return a list of capital city reports for that continent
     */
    List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Produces a list of capital city reports for capital cities in region organised by largest population to smallest
     * @param region the region to produce the reports from
     * @param limit the max amount of reports returned
     * @return a list of capital city reports for that region
     */
    List<CapitalCityReport> getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit);

    /**
     * @return a list of population reports for each continent.
     */
    List<PopulationReport> getPopulationOfPeopleInEachContinent();

    /**
     * @return a list of population reports for each regions.
     */
    List<PopulationReport> getPopulationOfPeopleInEachRegion();

    /**
     * @return a list of population reports for each country.
     */
    List<PopulationReport> getPopulationOfPeopleInEachCountry();

    /**
     * @return a population report for the whole world.
     */
    Long getThePopulationOfTheWorld();

    /**
     * Produces a population report for a specified continent.
     * @param continent the continent to generate the population report for
     * @return a population report for that continent.
     */
    SimplePopulationReport getThePopulationOfAContinent(String continent);

    /**
     * Produces a population report for a specified region.
     * @param region the region to generate the population report for
     * @return a population report for that region
     */
    SimplePopulationReport getThePopulationOfARegion(String region);

    /**
     * Produces a population reports for a specified country
     * @param country the country to generate the population report for
     * @return a population report for that country
     */
    SimplePopulationReport getThePopulationOfACountry(String country);

    /**
     * Produces a population report for a district.
     * @param district the district to generate the population report for
     * @return a population report for that district
     */
    SimplePopulationReport getThePopulationOfADistrict(String district);

    /**
     * Produces a population report for a city
     * @param city the city to generate the population report for
     * @return a population report for that city
     */
    SimplePopulationReport getThePopulationOfACity(String city);

    /**
     * Produces a language reports for specified languages.
     * @param languages the languages to create the
     * @return a language report for that languages
     */
    List<LanguageReport> getLanguageReport(String[] languages);

    /**
     * Calling this method will clean everything up.
     * This means calling other methods might throw an exceptions.
     * @return true if clean process was successful, false otherwise
     */
    boolean cleanup();
}
