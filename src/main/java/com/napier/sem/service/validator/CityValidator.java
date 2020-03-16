package com.napier.sem.service.validator;

import com.napier.sem.exceptions.ServiceException;

public class CityValidator implements Validator<String> {

    private NonNullOrEmptyValidator nonNullOrEmptyValidator = new NonNullOrEmptyValidator();
    @Override
    public void validate(String input) throws ServiceException {
        nonNullOrEmptyValidator.validate(input);
        if (input.contains("?") || input.contains("--")) {
            // TODO add better validation
            throw new ServiceException("Invalid characters in input");
        }
    }
}
