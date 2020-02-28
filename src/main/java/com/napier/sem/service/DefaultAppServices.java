package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.reports.*;

import java.util.List;


/**
 * This class is the default implementation of the AppService interface.
 * The implemented methods contain the actual business logic.
 */
public class DefaultAppServices implements AppServices {

    private final DataLayer dataLayer;

    public DefaultAppServices(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    @Override
    public List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest() {
        return dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getAllCountriesInAContinentOrderedByLargestPopulationToSmallest(String continent) {
        return dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest(continent, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getAllCountriesInARegionOrderedByLargestPopulationToSmallest(String region) {
        return dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest(region, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getCountriesFromWorldOrderedByLargestPopulationToSmallest(int limit) {
        return dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CountryReport> getCountriesFromContinentOrderedByLargestPopulationToSmallest(String continent, int limit) {
        return dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CountryReport> getCountriesFromRegionOrderedByLargestPopulationToSmallest(String region, int limit) {
        return dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest(region, limit);
    }

    @Override
    public List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest() {
        return dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Override
    public List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continent) {
        return dataLayer.getCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String region) {
        return dataLayer.getCitiesInARegionOrganisedByLargestPopulationToSmallest(region, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String country) {
        return dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest(country, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CityReport> getAllCitiesFromDistrictOrderedByLargestPopulationToSmallest(String district) {
        return dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest(district, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CityReport> getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return dataLayer.getCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return dataLayer.getCitiesInARegionOrganisedByLargestPopulationToSmallest(region, limit);
    }

    @Override
    public List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit) {
        return dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest(country, limit);
    }

    @Override
    public List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit) {
        return dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest(district, limit);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest() {
        return dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent) {
        return dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region) {
        return dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(region, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(region, limit);
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachContinent() {
        return dataLayer.getPopulationOfPeopleInEachContinent();
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachRegion() {
        return dataLayer.getPopulationOfPeopleInEachRegion();
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachCountry() {
        return dataLayer.getPopulationOfPeopleInEachCountry();
    }

    @Override
    public SimplePopulationReport getThePopulationOfTheWorld() {
        return dataLayer.getThePopulationOfTheWorld();
    }

    @Override
    public SimplePopulationReport getThePopulationOfAContinent(String continent) {
        return dataLayer.getThePopulationOfAContinent(continent);
    }

    @Override
    public SimplePopulationReport getThePopulationOfARegion(String region) {
        return dataLayer.getThePopulationOfARegion(region);
    }

    @Override
    public SimplePopulationReport getThePopulationOfACountry(String country) {
        return dataLayer.getThePopulationOfACountry(country);
    }

    @Override
    public SimplePopulationReport getThePopulationOfADistrict(String district) {
        return dataLayer.getThePopulationOfADistrict(district);
    }

    @Override
    public SimplePopulationReport getThePopulationOfACity(String city) {
        return dataLayer.getThePopulationOfACity(city);
    }

    @Override
    public List<LanguageReport> getLanguageReport(String[] languages) {
        return dataLayer.getLanguageReport(languages);
    }
}
