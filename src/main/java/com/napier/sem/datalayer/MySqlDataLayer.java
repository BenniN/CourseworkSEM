package com.napier.sem.datalayer;

import com.napier.sem.reports.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public MySqlDataLayer(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean initialize() {
        // load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Driver could not be loaded!");
        }
        DriverManager.setLoginTimeout(5); // five seconds timeout
        try {

            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/world?useSSL=false", this.user, this.password);
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
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CityReport> getCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Continent = '" + continent + "'\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CityReport> getCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Region = '" + region + "'\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CityReport> getCitiesInACountryOrganisedByLargestPopulationToSmallest(String country, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE cn.Name = '" + country + "'\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CityReport> getCitiesInADistrictOrganisedByLargestPopulationToSmallest(String district, int limit) {
        return produceCityReport("select c.name, c.population, c.district, cn.Name as country\n" +
                "FROM city c\n" +
                "JOIN country cn ON cn.Code = c.CountryCode\n" +
                "WHERE c.District = '" + district + "'\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInTheWorldOrganisedByLargestPopulationToSmallest(int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInAContinentOrganisedByLargestPopulationToSmallest(String continent, int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id AND co.Continent = '" + continent + "'\n" +
                "ORDER BY population DESC;", limit);
    }

    @Override
    public List<CapitalCityReport> getCapitalCitiesInARegionOrganisedByLargestPopulationToSmallest(String region, int limit) {
        return produceCapitalCityReport("SELECT c.name, c.population, co.name as country\n" +
                "FROM city c\n" +
                "JOIN country co on c.CountryCode = co.code\n" +
                "WHERE  co.Capital= c.id AND co.Region = '" + region + "'\n" +
                "ORDER BY population DESC;", limit);
    }


    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachContinent() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachRegion() {
        return producePopulationReport("SELECT cntry.Region as \"name\", sum(cntry.Population) as \"population_total\", sum(cty.Population)\n" +
                "FROM country cntry\n" +
                "JOIN city cty ON cty.CountryCode = cntry.Code\n" +
                "GROUP BY cntry.Region", DataLayer.NO_LIMIT);
    }

    @Override
    public List<PopulationReport> getPopulationOfPeopleInEachCountry() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SimplePopulationReport getThePopulationOfTheWorld() {
        return produceSimplePopulationReport ("select sum(population) as population from country;");
    }

    @Override
    public SimplePopulationReport getThePopulationOfAContinent(String continent) {
        return produceSimplePopulationReport("select continent as name, sum(population) as population from country\n" +
                "where continent = " + continent + ";");
    }

    @Override
    public SimplePopulationReport getThePopulationOfARegion(String region) {
        return produceSimplePopulationReport("select region as name, sum(population) as population from country\n" +
                "where region = " + region + ";");
    }

    @Override
    public SimplePopulationReport getThePopulationOfACountry(String country) {
        return produceSimplePopulationReport("select country as name, population as population from country\n" +
                "where Name  = " + country + " ;");
    }

    @Override
    public SimplePopulationReport getThePopulationOfADistrict(String district) {
        return produceSimplePopulationReport("SELECT district as name, SUM(population) as population from city\n" +
                "where district = '" +district + "'");
    }

    @Override
    public SimplePopulationReport getThePopulationOfACity(String city) {
        return produceSimplePopulationReport("select city as name, population as population from city\n" +
                "where Name  = " + city + ";");
    }

    @Override
    public List<LanguageReport> getLanguageReport(String[] languages) {
        String langList = buildLanguageSqlList(languages);
        return produceLanguageReport("SELECT cl.Language,\n" +
                "       sum(country.Population * cl.Percentage / 100) as total,\n" +
                "       sum(country.Population * cl.Percentage / 100) / (SELECT sum(country.Population) FROM country) * 100 as percentage\n" +
                "FROM countrylanguage cl\n" +
                "         JOIN country ON cl.CountryCode = country.Code\n" +
                "WHERE cl.Language in (" + langList + ")\n" +
                "GROUP BY cl.Language\n" +
                "ORDER BY total DESC;");
    }

    private String buildLanguageSqlList(String[] languages) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < languages.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append('\'');
            builder.append(languages[i]);
            builder.append('\'');
        }
        return builder.toString();
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
     * This is helper method for generating population reports.
     *
     * @param sql   the sql to generate the reports from.<br/>
     *              <strong>Note</strong> that the sql's column names must match the followings:
     *              <ul>
     *              <li>name</li>
     *              <li>population_total</li>
     *              <li>population_cities</li>
     *              </ul>
     * @param limit the max count of generated reports
     * @return a list containing the reports
     */
    private List<PopulationReport> producePopulationReport(String sql, int limit) {
        return produceReport(sql, limit, resultSet -> {
            String name = resultSet.getString("name");
            int populationTotal = resultSet.getInt("population_total");
            int populationCities = resultSet.getInt("population_cities");
            return new PopulationReport(name, populationTotal, populationCities);
        });
    }

    /**
     * Helper method for creating language reports
     *
     * @param sql the sql to generate the reports from.
     *            Use language, speakers and percentage as column names
     * @return a list containing the reports
     */
    private List<LanguageReport> produceLanguageReport(String sql) {
        return produceReport(sql, DataLayer.NO_LIMIT, resultSet -> {
            String language = resultSet.getString("language");
            int speakers = resultSet.getInt("speakers");
            double percentage = resultSet.getDouble("percentage");
            return new LanguageReport(language, speakers, percentage);
        });
    }

    /**
     * Helper method for generating a simple population report.
     * The specified sql will be executed but only the first result will be returned
     * or null if there is no result. <br/>
     * <strong>Note</strong> that the sql column names must match 'name' and 'population' to get proper results.
     *
     * @param sql the sql to execute
     * @return the first result of that sql as simple population report, or null if there are no results
     */
    private SimplePopulationReport produceSimplePopulationReport(String sql) {
        List<SimplePopulationReport> reports = produceReport(sql, 1, resultSet -> {
            String name = resultSet.getString("name");
            int population = resultSet.getInt("population");
            return new SimplePopulationReport(name, population);
        });
        if (reports.isEmpty()) {
            return null;
        }
        return reports.get(0);
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
