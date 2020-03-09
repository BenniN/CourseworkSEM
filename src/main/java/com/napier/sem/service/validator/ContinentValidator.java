package com.napier.sem.service.validator;

import com.napier.sem.exceptions.ServiceException;

import java.util.List;

public class ContinentValidator implements Validator<String> {

    private List<String> continents = List.of("Europe", "Asia", "North America", "Africa", "Oceania", "Antarctica", "South America");
    private NonNullOrEmptyValidator nonNullOrEmptyValidator = new NonNullOrEmptyValidator();

    @Override
    public void validate(String input) throws ServiceException {
        nonNullOrEmptyValidator.validate(input);
        for (String continent : continents) {
            if (continent.equalsIgnoreCase(input)) {
                return;
            }
        }
        throw new ServiceException("The input is no real continent");
    }
}
