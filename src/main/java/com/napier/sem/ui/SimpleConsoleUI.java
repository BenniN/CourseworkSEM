package com.napier.sem.ui;

import com.napier.sem.service.AppServices;
import com.napier.sem.utils.Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Very simple implementation for showing reports on the console.
 */
public class SimpleConsoleUI {

    private static final Logger LOGGER = LogManager.getLogger(SimpleConsoleUI.class);

    private AppServices appServices;

    public SimpleConsoleUI(AppServices appServices) {
        this.appServices = appServices;
    }

    public void start() {
        LOGGER.debug("starting console application");
        printAsciiArt();
        execute((appServices) -> System.out.println(appServices.getAllCountriesOrderedByLargestPopulationToSmallest()));
    }

    private void printAsciiArt() {
        System.out.println(Utils.getFileAsString("/title_ascii.txt"));
    }

    private void execute(ServiceCall call) {
        try {
            call.perform(appServices);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }



}
