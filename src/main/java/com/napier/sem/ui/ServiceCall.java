package com.napier.sem.ui;

import com.napier.sem.service.AppServices;

/**
 * A service call is used to call methods from the AppService interface.
 */
public interface ServiceCall {

    /**
     * This method contains calls using the AppServices interface.
     * For graphical user interfaces this might be useful, since demanding
     * database calls can be executed in a separate thread.
     * @param appServices the code to execute
     * @throws RuntimeException if during the execution an exception is thrown
     */
    void perform(AppServices appServices) throws RuntimeException;

}