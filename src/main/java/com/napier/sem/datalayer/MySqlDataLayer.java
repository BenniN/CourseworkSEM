package com.napier.sem.datalayer;

import com.napier.sem.reports.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This implementation of the <code>DataLayer</code> provides
 * access to a MySQL database which connection details are
 * provided by a file called <em>connection.properties</em>.
 */
public class MySqlDataLayer implements DataLayer {

    private static final Logger LOGGER = LogManager.getLogger(MySqlDataLayer.class);

    // connection properties
    private String host;
    private String user;
    private String password;

    private Connection connection;

    /**
     * This constructor simply loads the connection details
     * from the connection.properties file.
     * No connection will be set up at this point!
     */
    public MySqlDataLayer() {
        Properties connectionProps = new Properties();
        try {
            LOGGER.debug("Loading connection properties file");

            // read connection properties from file
            connectionProps.load(getClass().getResourceAsStream("/connection.properties"));

            // read properties
            this.host = connectionProps.getProperty("db_host");
            this.user = connectionProps.getProperty("db_user");
            this.password = connectionProps.getProperty("db_password");

            LOGGER.debug("Successfully read connection properties");
        } catch (IOException e) {
            LOGGER.error("Error while loading / reading properties file: " + e.getMessage());
        }
    }

    @Override
    public boolean initialize() {
        DriverManager.setLoginTimeout(5); // five seconds timeout
        try {
            this.connection = DriverManager.getConnection(this.host, this.user, this.password);
            LOGGER.debug("Successfully established connection to host");
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public List<CountryReport> getCountriesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return produceCountryReport("SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name as capital\n" +
                "FROM country c\n" +
                "JOIN city ci ON ci.ID = c.Capital\n" +
                "ORDER BY Population DESC", limit);
    }

    @Override
    public List<CountryReport> getCountriesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return produceCountryReport("SELECT c.Code as code, c.Name, c.Continent, c.Region, c.Population, ci.Name as capital\n" +
                "FROM country c\n" +
                "JOIN city ci ON ci.ID = c.Capital\n" +
                "WHERE c.Continent = '" + continent + "'\n" +
                "ORDER BY Population DESC;", limit);
    }

    @Override
    public List<CountryReport> getCountriesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return produceCountryReport("SELECT c.Code as code, c.Name, c.Continent, c.Region, c.Population, ci.Name as capital\n" +
                "FROM country c\n" +
                "JOIN city ci ON ci.ID = c.Capital\n" +
                "WHERE c.Region = '" + region + "'\n" +
                "ORDER BY Population DESC;", limit);
    }

    @Override
    public List<CityReport> getCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Continent = '" + continent + "'\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Region = '" + region + "'\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Name = '" + country + "'\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE c.District = '" + district + "'\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id AND co.Continent = '" + continent + "'\n" +
                "ORDER BY population DESC", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id AND co.Region = '" + region + "'\n" +
                "ORDER BY population DESC", limit);
    }


    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachContinent() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachRegion() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachCountry() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfTheWorld() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfAContinent(String continent) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfARegion(String region) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfACountry(String country) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfADistrict(String district) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public PopulationReport getThePopulationOfACity(String city) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public LanguageReport getLanguageReport(String[] languages) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * This is a helper method for generating country reports.
     *
     * @param sql   the sql to produce the report from.<br/>
     *              <strong>Note</strong> that in the specified sql the column names have to match the following
     *              to get proper results:
     *              <ul>
     *              <li>code</li>
     *              <li>name</li>
     *              <li>continent</li>
     *              <li>region</li>
     *              <li>population</li>
     *              <li>capital</li>
     *              </ul>
     * @param limit the max count of reports
     * @return a list of country reports based on the specified sql.
     */
    private List<CountryReport> produceCountryReport(String sql, int limit) {
        return produceReport(sql, limit, resultSet -> {
            String code = resultSet.getString("code");
            String name = resultSet.getString("name");
            String continent = resultSet.getString("continent");
            int population = resultSet.getInt("population");
            String region = resultSet.getString("region");
            String capital = resultSet.getString("capital");
            return new CountryReport(code, name, continent, region, population, capital);
        });
    }

    /**
     * This is a helper method for generating city reports.
     *
     * @param sql   the sql to produce the report from. <br/>
     *              <strong>Note</strong> that the sql column names must match the followings:
     *              <ul>
     *              <li>name</li>
     *              <li>population</li>
     *              <li>district</li>
     *              <li>country</li>
     *              </ul>
     * @param limit the max rows for this report
     * @return a list containing the city reports
     */
    private List<CityReport> produceCityReport(String sql, int limit) {
        return produceReport(sql, limit, resultSet -> {
            String name = resultSet.getString("name");
            int population = resultSet.getInt("population");
            String district = resultSet.getString("district");
            String country = resultSet.getString("country");
            return new CityReport(name, country, district, population);
        });
    }

    /**
     * This is helper method for generating capital city reports.
     *
     * @param sql   the sql to generate the reports from.<br/>
     *              <strong>Note</strong> that the sql's column names must match the followings:
     *              <ul>
     *              <li>name</li>
     *              <li>country</li>
     *              <li>population</li>
     *              </ul>
     * @param limit the max count of generated reports
     * @return a list containing the reports
     */
    private List<CapitalCityReport> produceCapitalCityReport(String sql, int limit) {
        return produceReport(sql, limit, resultSet -> {
            String name = resultSet.getString("name");
            String country = resultSet.getString("country");
            int population = resultSet.getInt("population");
            return new CapitalCityReport(name, country, population);
        });
    }

    /**
     * Generic helper method for generating a list with reports.
     *
     * @param sql      the sql to execute
     * @param limit    the limit of reports (max rows)
     * @param callback the callback method will be called to process a result from the ResultSet object
     * @param <T>      the type of report being generated
     * @return a list of reports of the specified type
     */
    private <T> List<T> produceReport(String sql, int limit, ReportGenerationCallback<T> callback) {
        List<T> reportResult = new ArrayList<>();
        try {
            ResultSet resultSet = executeSQLAndReturnResultSet(sql, limit);
            while (resultSet.next()) {
                T report = callback.process(resultSet);
                reportResult.add(report);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return reportResult;
    }

    /**
     * Functional interface used by the <code>produceReport()</code>
     * method to generate a report object for a given result of the ResultSet
     *
     * @param <T> the type of report that will be returned
     */
    private interface ReportGenerationCallback<T> {

        /**
         * Callback method. Implementations must create an object of the specified type.
         *
         * @param resultSet used to extract data from current result. Call any get...() method you want.
         * @return an object of the specified type
         * @throws SQLException if data could not be extracted.
         */
        T process(ResultSet resultSet) throws SQLException;
    }

    /**
     * Executes the specified sql statement and returns the result as ResultSet object.
     *
     * @param sql the sql to execute
     * @return a result set object containing the results
     */
    private ResultSet executeSQLAndReturnResultSet(String sql, int maxRows) {
        try {
            Statement statement = connection.createStatement();
            if (maxRows > 0) {
                statement.setMaxRows(maxRows);
            }
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanup() {
        if (this.connection == null) {
            LOGGER.debug("There is no current connection to close.");
            return true;
        }
        try {
            // close connection to host and set variable to null
            this.connection.close();
            this.connection = null;
            LOGGER.debug("Connection closed successfully!");
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}
