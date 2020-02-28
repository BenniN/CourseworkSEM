package com.napier.sem.reports;

/**
 * This type of population report only consist of the name and population
 * of a certain area (e.g. world, region, continent, district or country).
 */
public final class SimplePopulationReport {

    private String name;
    private int population;

    /**
     * Creates a new simple population report with the specified name and population
     * @param name the name of the population area
     * @param population the total population for that area
     */
    public SimplePopulationReport(String name, int population) {
        this.name = name;
        this.population = population;
    }

    /**
     * @return the name of the requested population report's area
     */
    public String getName() {
        return name;
    }

    /**
     * @return the total population in that area
     */
    public int getPopulation() {
        return population;
    }
}
