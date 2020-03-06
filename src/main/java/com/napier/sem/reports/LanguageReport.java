package com.napier.sem.reports;

import com.napier.sem.utils.InputValidator;

/**
 * A Language Report contains information on one language
 * such as the name of the language, the number of people speaking
 * that language and the percentage of the world population.
 */
public final class LanguageReport {

    private final String language;
    private int speakers;
    private double percentage;

    /**
     * Creates a new language report based on the specified information.
     * @param language the name of the language
     * @param speakers the number of people speaking that language
     * @param percentage the percentage of the world population
     */
    public LanguageReport(String language, int speakers, double percentage) {
        InputValidator.checkNotNull(language, speakers, percentage);
        InputValidator.checkNotNegative(speakers);
        InputValidator.checkNotNegative(percentage);
        this.language = language;
        this.speakers = speakers;
        this.percentage = percentage;
    }

    /**
     * @return the name of the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return the number of people speaking that language
     */
    public int getSpeakers() {
        return speakers;
    }

    /**
     * @return the percentage of the world population
     */
    public double getPercentage() {
        return percentage;
    }
}
