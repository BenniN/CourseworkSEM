package com.napier.sem.service;

import com.napier.sem.datalayer.DataLayer;
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
        ResultSet resultSet = dataLayer.executeNativeQuery("select * from country");
        List<CountryReport> resultList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            String continent = resultSet.getString("Continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("Region");
            CountryReport report = new CountryReport(code, name, continent, region, population, "TODO:capital");
            resultList.add(report);
        }
        return resultList;
    }
}
