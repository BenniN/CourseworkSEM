package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.reports.CityReport;
import com.napier.sem.reports.CountryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CountryReport> getAllCountriesOrderedByLargestPopulationToSmallest() throws SQLException {
        // get data from database
        ResultSet resultSet = dataLayer.executeNativeQuery(Reports.ALL_COUNTRIES_ORDERED_BY_LARGEST_POPULATION_TO_SMALLEST);
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            // extract values from result set
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            String capital = resultSet.getString("Capital");

            // create country report
            CountryReport report = new CountryReport(code, name, continent, region, population, capital);
            resultList.add(report);
        }
        return resultList;
    }

    @Override
    public List<CityReport> getAllCitiesInTheWorldOrderedByLargestPopulationToSmallest() throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select name, population, district, countryCode FROM city\n" +
                "ORDER BY population DESC");
        List<CityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            int population = resultSet.getInt("population");
            String district = resultSet.getString("District");
            String countryCode = resultSet.getString("Country Code ");
            CityReport report = new CityReport(name, countryCode, district, population);
            resultList.add(report);
        }
        return resultList;

    }

    @Override
    public List<CityReport> getAllCitiesFromContinentOrderedByLargestPopulationToSmallest(String continentsql) throws SQLException {
        ResultSet resultSet = dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where continent = " + continentsql + " " +
                "ORDER BY population DESC");
        List<CityReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String countryCode = resultSet.getString("Country Code ");
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            int population = resultSet.getInt("population");
            CityReport report = new CityReport(name, countryCode, district, population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getAllCitiesFromRegionOrderedByLargestPopulationToSmallest(String regionsql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where Region = " + regionsql + " "+
                "ORDER BY population DESC");
        List<CityReport> resultList =new ArrayList<>();
        while (resultSet.next()){

            String countryCode = resultSet.getString("Country Code ");
            int population = resultSet.getInt("population");
            String district = resultSet.getString("District");
            String name = resultSet.getString("Name");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }
    @Override
    public List<CityReport> getAllCitiesFromCountryOrderedByLargestPopulationToSmallest(String countrysql) throws SQLException {
        ResultSet resultSet =dataLayer.executeNativeQuery("select c.name, c.population, district, c.CountryCode \n" +
                "FROM city c\n" +
                "inner join country co on c.CountryCode = co.code\n" +
                "where  co.Name = " + countrysql + " " +
                "ORDER BY population DESC");
        List<CityReport> resultList =new ArrayList<>();
        while (resultSet.next()){
            int population = resultSet.getInt("population");
            String countryCode = resultSet.getString("Country Code ");
            String name = resultSet.getString("Name");
            String district = resultSet.getString("District");
            CityReport report = new CityReport(name,countryCode,district,population);
            resultList.add(report);
        }
        return resultList;
    }
}
