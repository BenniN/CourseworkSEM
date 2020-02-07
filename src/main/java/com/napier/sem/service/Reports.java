package com.napier.sem.service;

/**
 * This class holds all sql queries for the various reports.
 */
public final class Reports {

    public static final String ALL_COUNTRIES_ORDERED_BY_LARGEST_POPULATION_TO_SMALLEST =
            "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, c.Capital " +
            "FROM country c " +
            "ORDER BY Population DESC";
}
