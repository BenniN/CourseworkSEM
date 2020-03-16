package com.napier.sem.service;

import com.napier.sem.reports.*;

import java.util.List;

/**
 * The <code>AppServices</code> interface defines all types of reports that can be generated.
 */
public interface AppServices {

    /**
     * Generates a list of reports with all the countries in the world organised by largest population to smallest.
     *
     * @return a ordered list containing all country reports
     */
    List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest();

    /**
     * Generates a list of reports with all countries for a specified continent ordered by largest population to smallest.
     *
     * @param continent the continent to filter for
     * @return an ordered list containing all countries for the specified continent
     */
    List<CountryReport> getAllCountriesInAContinentOrderedByLargestPopulationToSmallest(String continent);

    /**
     * Generates a list of reports with all countries in a specified region ordered by largest population to smallest
     *
     * @param region the region to filter for
     * @return an ordered list containing all countries for the specified region
     */
    List<CountryReport> getAllCountriesInARegionOrderedByLargestPopulationToSmallest(String region);

    /**
     * Generates a list of reports with countries in the world organized by largest population to smallest
     *
     * @param limit is the input for the amount of countries which should be presented
     * @return a ordered list containing N countries in the world
     */
    List<CountryReport> getCountriesFromWorldOrderedByLargestPopulationToSmallest(int limit);

    /**
     * Generates a list of reports with N countries in a continent organized by largest population to smallest
     *
     * @param continent is the input for a specific continent
     * @param limit     is the input for the amount of countries in the specific continent which should be presented
     * @return a ordered list containing N countries of the selected continent
     */
    List<CountryReport> getCountriesFromContinentOrderedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Generates a list of reports with N countries in a region organized by largest population to smallest
     *
     * @param region is the input to specify the region
     * @param limit  is the input for the amount of countries in the specific region which should be presented
     * @return a ordered list containing N countries of the selected region
     */
    List<CountryReport> getCountriesFromRegionOrderedByLargestPopulationToSmallest(String region, int limit);

    /**
     * Generates a list of reports with all cities in the world organized by largest population to smallest
     *
     * @return a ordered list containing all cities in the world
     */
    List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest();

    /**
     * Generates a list of reports with all of the cities in a user specified continent, organised from largest to smallest
     *
     * @param continent is the input from the user of which continent should be presented
     * @return an ordered list from largest population to smallest population of that continent
     */
    List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continent);

    /**
     * Generates a list of reports with all cities in a region organized by largest population to smallest
     *
     * @param region is the input for a specific region
     * @return a ordered list containing all cities in the selected region
     */
    List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String region);

    /**
     * Generates a list of reports with all cities in a country organized by largest population to smallest
     *
     * @param country is the input for a specific country
     * @return a ordered list containing all cities in the selected country
     */
    List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String country);

    /**
     * Generates a list of reports with all cities in a district organized by largest population to smallest
     *
     * @param district is the input for a specific district
     * @return a ordered list containing all cities in the selected district
     */
    List<CityReport> getAllCitiesFromDistrictOrderedByLargestPopulationToSmallest(String district);

    /**
     * Generates a list of reports with N Cities in the world organized by largest population to smallest
     *
     * @param limit is the input for the amount of Cities which should be presented
     * @return a ordered list containing N Cities of the world
     */
    List<CityReport> getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit);

    /**
     * Produces a list of city reports for cities in a continent organised by largest population to smallest.
     *
     * @param continent the continent to get city reports from
     * @param limit     the max amount of reports returned
     * @return an ordered list for city reports for that continent
     */
    List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Produces a list of city reports for cities in a region organised by largest population to smallest.
     *
     * @param region the region to get the city reports from
     * @param limit  the max amount of reports returned
     * @return an ordered list of city reports for that region
     */
    List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit);

    /**
     * Produces a list of city reports for cities in a country organised by largest population to smallest.
     *
     * @param country the country to get the city reports from
     * @param limit   the max amount of reports returned
     * @return an ordered list of city reports for that country
     */
    List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit);

    /**
     * Produces a list of city reports for cities in a district organised by largest population to smallest.
     *
     * @param district the district to get the city reports from
     * @param limit    the max amount of reports returned
     * @return an ordered list of city reports for that district
     */
    List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit);

    /**
     * Produces a list of capital city reports for all capital cities in the world organised by largest population to smallest.
     *
     * @return an ordered list of capital city reports
     */
    List<CapitalCityReport> getAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest();

    /**
     * Produces a list of capital city reports for capital cities in a continent organised by largest population to smallest.
     *
     * @param continent the continent to generate the capital city reports from
     * @return an ordered list of capital city reports for that continent
     */
    List<CapitalCityReport> getAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent);

    /**
     * Generates a list of reports with all capital cities from a certain region ordered by largest population to smallest.
     *
     * @param region the region to filter for
     * @return an ordered list of capital cities for the specified region
     */
    List<CapitalCityReport> getAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region);

    /**
     * Generates a list of reports with N Capital Cities in the world organized by largest population to smallest
     *
     * @param limit is the input for the amount of Capital Cities which should be presented
     * @return a ordered list containing N Capital Cities of the world
     */
    List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit);

    /**
     * Generates a list of capital city reports in a specified continent organised by largest population to smallest.
     *
     * @param continent the continent to get all capital cities from
     * @param limit     the max amount of reports returned
     * @return an ordered list of reports with all capital cities for that continent
     */
    List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit);

    /**
     * Generates a list of capital city reports for a specific region organised by largest population to smallest.
     *
     * @param region the region to get all capital cities from
     * @param limit  the max amount of reports returned
     * @return an ordered list of reports with all capital cities for that region
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
     *
     * @param continent the continent to generate the population report for
     * @return a population report for that continent.
     */
    SimplePopulationReport getThePopulationOfAContinent(String continent);

    /**
     * Produces a population report for a specified region.
     *
     * @param region the region to generate the population report for
     * @return a population report for that region
     */
    SimplePopulationReport getThePopulationOfARegion(String region);

    /**
     * Produces a population reports for a specified country
     *
     * @param country the country to generate the population report for
     * @return a population report for that country
     */
    SimplePopulationReport getThePopulationOfACountry(String country);

    /**
     * Produces a population report for a district.
     *
     * @param district the district to generate the population report for
     * @return a population report for that district
     */
    SimplePopulationReport getThePopulationOfADistrict(String district);

    /**
     * Produces a population report for a city
     *
     * @param city the city to generate the population report for
     * @return a population report for that city
     */
    SimplePopulationReport getThePopulationOfACity(String city);

    /**
     * Produces a language reports for specified languages.
     *
     * @param languages the languages to create the
     * @return a language report for that languages
     */
    List<LanguageReport> getLanguageReport(String[] languages);

}
