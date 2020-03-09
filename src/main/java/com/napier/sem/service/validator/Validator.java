package com.napier.sem.service.validator;

import com.napier.sem.exceptions.ServiceException;

/**
 * Interface for validating inputs
 * @param <T> the data type of the object to validate
 */
public interface Validator<T> {

    void validate(T input) throws ServiceException;

}
