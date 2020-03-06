package com.napier.sem.reports;

import com.napier.sem.utils.InputValidator;

/**
 * A CityReport contains a cities data such as name, country, district and its population.
 * This class is immutable.
 */
public final class CityReport {

    private final String name;
    private final String country;
    private final String district;
    private final Integer population;

    /**
     * Creates a new CityReport for the specified values.
     * @param name the name of the city
     * @param country the country of the city
     * @param district the district of the city
     * @param population the population of the city
     */
    public CityReport(String name, String country, String district, Integer population) {
        InputValidator.checkNotNull(name, country, district, population);
        InputValidator.checkNotNegative(population);
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    /**
     * @return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * @return the country of the city
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the district of the city
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @return the population of the city
     */
    public Integer getPopulation() {
        return population;
    }
}
