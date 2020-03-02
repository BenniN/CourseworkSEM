package com.napier.sem;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.datalayer.MySqlDataLayer;
import com.napier.sem.service.AppServices;
import com.napier.sem.service.DefaultAppServices;
import com.napier.sem.ui.SimpleConsoleUI;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class ApplicationInitializer {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationInitializer.class);

    private DataLayer dataLayer;
    private AppServices appServices;

    private static final int RETRIES = 50;
    private static final int WAITING_TIME_IN_BETWEEN = 5000; // in milliseconds

    public ApplicationInitializer(String host, String user, String password) {
        // init layer instances
        this.dataLayer = new MySqlDataLayer(host, user, password);
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

    public static void main(String[] args) {
        if (args.length == 3) {
            new ApplicationInitializer(args[0], args[1], args[2]);
        } else {
            Properties connectionProps = new Properties();
            try {
                LOGGER.debug("Loading connection properties file");

                // read connection properties from file
                connectionProps.load(ApplicationInitializer.class.getResourceAsStream("/connection.properties"));

                // read properties
                String hostProp = connectionProps.getProperty("db_host");
                String userProp = connectionProps.getProperty("db_user");
                String passwordProp = connectionProps.getProperty("db_password");
                LOGGER.debug("Successfully read connection properties");

                new ApplicationInitializer(hostProp, userProp, passwordProp);
            } catch (IOException e) {
                LOGGER.error("Error while loading / reading properties file: " + e.getMessage());
            }
        }

    }

}
