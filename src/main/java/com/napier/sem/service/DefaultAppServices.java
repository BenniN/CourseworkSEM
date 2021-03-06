package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.reports.*;
import com.napier.sem.service.validator.*;

import java.util.List;


/**
 * This class is the default implementation of the AppService interface.
 * The implemented methods contain the actual business logic.
 */
public class DefaultAppServices implements AppServices {

    private final DataLayer dataLayer;

    Validator<String> continentValidator = new ContinentValidator();
    Validator<String> regionValidator = new RegionValidator();
    Validator<Integer> limitValidator = new LimitValidator();
    Validator<String> countryValidator = new CountryValidator();
    Validator<String> districtValidator = new DistrictValidator();
    Validator<String> cityValidator = new CityValidator();

    public DefaultAppServices(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    @Override
    public List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest() {
        return dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getAllCountriesInAContinentOrderedByLargestPopulationToSmallest(String continent) {
        this.continentValidator.validate(continent);
        return dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest(continent, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getAllCountriesInARegionOrderedByLargestPopulationToSmallest(String region) {
        this.regionValidator.validate(region);
        return dataLayer.getCountriesInARegionOrganisedByLargestPopulationToSmallest(region, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CountryReport> getCountriesFromWorldOrderedByLargestPopulationToSmallest(int limit) {
        this.limitValidator.validate(limit);
        return dataLayer.getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CountryReport> getCountriesFromContinentOrderedByLargestPopulationToSmallest(String continent, int limit) {
        this.continentValidator.validate(continent);
        this.limitValidator.validate(limit);
        return dataLayer.getCountriesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CountryReport> getCountriesFromRegionOrderedByLargestPopulationToSmallest(String region, int limit) {
        this.limitValidator.validate(limit);
        this.regionValidator.validate(region);
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
        limitValidator.validate(limit);
        return dataLayer.getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        limitValidator.validate(limit);
        continentValidator.validate(continent);
        return dataLayer.getCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        limitValidator.validate(limit);
        regionValidator.validate(region);
        return dataLayer.getCitiesInARegionOrganisedByLargestPopulationToSmallest(region, limit);
    }

    @Override
    public List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit) {
        limitValidator.validate(limit);
        countryValidator.validate(country);
        return dataLayer.getCitiesInACountryOrganisedByLargestPopulationToSmallest(country, limit);
    }

    @Override
    public List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit) {
        limitValidator.validate(limit);
        districtValidator.validate(district);
        return dataLayer.getCitiesInADistrictOrganisedByLargestPopulationToSmallest(district, limit);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest() {
        return dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent) {
        this.continentValidator.validate(continent);
        return dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getAllCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region) {
        this.regionValidator.validate(region);
        return dataLayer.getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(region, DataLayer.NO_LIMIT);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        this.limitValidator.validate(limit);
        return dataLayer.getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        this.continentValidator.validate(continent);
        this.limitValidator.validate(limit);
        return dataLayer.getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(continent, limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        this.regionValidator.validate(region);
        this.limitValidator.validate(limit);
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
    public Long getThePopulationOfTheWorld() {
        return dataLayer.getThePopulationOfTheWorld();
    }

    @Override
    public SimplePopulationReport getThePopulationOfAContinent(String continent) {
        continentValidator.validate(continent);
        return dataLayer.getThePopulationOfAContinent(continent);
    }

    @Override
    public SimplePopulationReport getThePopulationOfARegion(String region) {
        regionValidator.validate(region);
        return dataLayer.getThePopulationOfARegion(region);
    }

    @Override
    public SimplePopulationReport getThePopulationOfACountry(String country) {
        countryValidator.validate(country);
        return dataLayer.getThePopulationOfACountry(country);
    }

    @Override
    public SimplePopulationReport getThePopulationOfADistrict(String district) {
        districtValidator.validate(district);
        return dataLayer.getThePopulationOfADistrict(district);
    }

    @Override
    public SimplePopulationReport getThePopulationOfACity(String city) {
        cityValidator.validate(city);
        return dataLayer.getThePopulationOfACity(city);
    }

    @Override
    public List<LanguageReport> getLanguageReport(String[] languages) {
        return dataLayer.getLanguageReport(languages);
    }

}
