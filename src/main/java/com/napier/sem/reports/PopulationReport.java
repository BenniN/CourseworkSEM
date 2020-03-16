package com.napier.sem.reports;

import com.napier.sem.utils.InputValidator;

/**
 * The immutable class PopulationReport can be used for continents / regions / countries.
 * It contains data about the population in total, in cities and outside cities (including a percentage).
 */
public final class PopulationReport {

    private final String name;
    private final Long totalPopulation;
    private final Long populationCities;
    private final Long populationNoCities;
    private final String populationNoCitesPercentage;
    private final String populationCitiesPercentage;

    /**
     * Creates a PopulationReport with the specified values.
     * Note: The population outside cities is computed based on the values of <code>totalPopulation</code>
     * and <code>populationCities</code>.
     * @param name the name of this continent / region / country
     * @param totalPopulation the total population of this continent / region / country
     * @param populationCities the total population in cites of this continent / region / country
     */
    public PopulationReport(String name, Long totalPopulation, Long populationCities) {
        InputValidator.checkNotNull(name, totalPopulation, populationCities);
        InputValidator.checkNotNegative(totalPopulation);
        InputValidator.checkNotNegative(populationCities);

        // the following is commented out due to database issues (issue country: gibraltar)
        /*
        if (totalPopulation < populationCities) {
            throw new IllegalArgumentException("total population cannot be higher than the (total) population in cities." +
                    " Problem name: " + name + ", total population: " + totalPopulation + ", city population " + populationCities);
        }*/

        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationCities = populationCities;

        // compute population in non-cities
        this.populationNoCities = totalPopulation - populationCities;

        // compute percentage for population in and outside cities
        this.populationCitiesPercentage = getPercentageFormat((double) populationCities / totalPopulation);
        this.populationNoCitesPercentage = getPercentageFormat((double) populationNoCities / totalPopulation);
    }

    /**
     * Converts the specified value to a percentage representation.
     * Example: An input value of '0.702' will be converted to '70.2%'
     * @param value the value to be converted to percentage
     * @return the percentage representation of the specified value
     */
    private String getPercentageFormat(double value) {
        return String.format("%3.1f", value * 100f) + "%";
    }

    /**
     * @return the name of this continent / region / country
     */
    public String getName() {
        return name;
    }

    /**
     * @return the total population of this continent / region / country
     */
    public Long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * @return the population in cities of this continent / region / country
     */
    public Long getPopulationCities() {
        return populationCities;
    }

    /**
     * @return the population outside cities of this continent / region / country
     */
    public Long getPopulationNoCities() {
        return populationNoCities;
    }

    /**
     * @return the population outside cities as percentage of this continent / region / country
     */
    public String getPopulationNoCitesPercentage() {
        return populationNoCitesPercentage;
    }

    /**
     * @return the population inside cities as percentage of this continent / region / country
     */
    public String getPopulationCitiesPercentage() {
        return populationCitiesPercentage;
    }
}
