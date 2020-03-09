package com.napier.sem.service.validator;

import com.napier.sem.exceptions.ServiceException;

public class NonNullOrEmptyValidator implements Validator<String> {
    @Override
    public void validate(String input) throws ServiceException {
        if (input == null || input.isEmpty()) {
            throw new ServiceException("The input is not allowed to be empty!");
        }
    }
}
