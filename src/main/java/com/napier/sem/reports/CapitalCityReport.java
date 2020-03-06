package com.napier.sem.reports;

import com.napier.sem.utils.InputValidator;

/**
 * This (immutable) class represents reports for capital cities.
 * In fact, it contains the name, country and population for a capital city.
 */
public final class CapitalCityReport {

    private final String name;
    private final String country;
    private final Integer population;

    /**
     * Creates a new capital city report with the specified values.
     * @param name the name of the capital city
     * @param country the country of the capital city
     * @param population the population of the capital city
     */
    public CapitalCityReport(String name, String country, Integer population) {
        InputValidator.checkNotNull(name, country, population);
        InputValidator.checkNotNegative(population);
        this.name = name;
        this.country = country;
        this.population = population;
    }

    /**
     * @return the name of the capital city
     */
    public String getName() {
        return name;
    }

    /**
     * @return the country of the capital city
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the population of the capital city
     */
    public Integer getPopulation() {
        return population;
    }
}
