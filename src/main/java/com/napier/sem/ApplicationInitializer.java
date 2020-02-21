package com.napier.sem;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.datalayer.MySqlDataLayer;
import com.napier.sem.service.AppServices;
import com.napier.sem.service.DefaultAppServices;
import com.napier.sem.ui.SimpleConsoleUI;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicationInitializer {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationInitializer.class);

    private DataLayer dataLayer;
    private AppServices appServices;

    private static final int RETRIES = 50;
    private static final int WAITING_TIME_IN_BETWEEN = 5000; // in milliseconds

    public ApplicationInitializer() {
        this.dataLayer = new MySqlDataLayer();
        this.appServices = new DefaultAppServices(dataLayer);

        if (establishConnection()) {
            // set up user interface
            initUI();
        } else {
            LOGGER.info("Closing application since connection could not be established.");
        }

    }

    private boolean establishConnection() {
        LOGGER.debug("Connection to data storage is being set up...");
        int retryCounter = RETRIES;
        boolean connected = false;
        while (retryCounter > 0) {
            LOGGER.debug("Connecting to database...");
            connected = dataLayer.initialize();
            if (connected) {
                break;
            }
            retryCounter--; // we could not connect, decrement counter
            LOGGER.debug("... connection could not be established! " + retryCounter + " times to go");

            try {
                Thread.sleep(WAITING_TIME_IN_BETWEEN);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        return connected;
    }

    private void initUI() {
        SimpleConsoleUI simpleConsoleUI = new SimpleConsoleUI(this.appServices);
        simpleConsoleUI.start();
    }

}
