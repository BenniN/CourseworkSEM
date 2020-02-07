package com.napier.sem.reports;

/**
 * A CountryReport is a collection of data regarding one specifc country.
 * This class is immutable.
 */
public final class CountryReport {

    private final String code;
    private final String name;
    private final String continent;
    private final String region;
    private final Integer population;
    private final String capital;

    /**
     * Creates a CountryReport with the specified values.
     * @param code the country code. The code must consist out of 3 characters
     * @param name the name of this country
     * @param continent the continent this country belongs to
     * @param region the region this country belongs to
     * @param population the population in this country
     * @param capital the capital of this country
     */
    public CountryReport(String code, String name, String continent, String region, Integer population, String capital) {
        if (code.length() != 3) {
            throw new IllegalArgumentException("the countries code must consist out of 3 characters");
        }
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    /**
     * @return the country code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * @return the continent of the country
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @return the region of the country
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return the population of the country
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * @return the capital of the country
     */
    public String getCapital() {
        return capital;
    }

    @Override
    public String toString() {
        return "CountryReport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                '}';
    }
}
