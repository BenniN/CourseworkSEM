package com.napier.sem.service.validator;

import com.napier.sem.exceptions.ServiceException;

public class LimitValidator implements Validator<Integer> {

    @Override
    public void validate(Integer input) throws ServiceException {
        if (input == null) {
            throw new ServiceException("Input must be provided!");
        }
        if (input <= 0) {
            throw new ServiceException("provided limit must at least be 1");
        }
    }
}
