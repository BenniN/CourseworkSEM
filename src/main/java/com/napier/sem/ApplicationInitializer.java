package com.napier.sem;

import com.napier.sem.datalayer.DataLayer;
import com.napier.sem.datalayer.MySqlDataLayer;
import com.napier.sem.service.AppServices;
import com.napier.sem.service.DefaultAppServices;
import com.napier.sem.ui.JavaFxApplication;
import com.napier.sem.ui.SimpleConsoleUI;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static javafx.application.Application.launch;

/**
 * The ApplicationInitializer is a singleton class, that sets up everything for application start.
 * It will set up a database connection, creates the business interface and eventually
 * starts a user interface.
 */
public class ApplicationInitializer {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationInitializer.class);

    private static ApplicationInitializer applicationInitializer = new ApplicationInitializer();

    private DataLayer dataLayer;
    private AppServices appServices;

    private static final int RETRIES = 50;
    private static final int WAITING_TIME_IN_BETWEEN = 5000; // in milliseconds

    private ApplicationInitializer() {
        // private constructor
    }

    public static ApplicationInitializer getInstance() {
        return applicationInitializer;
    }

    public void setHostInformation(String host, String user, String password) {
        // init layer instances
        dataLayer = new MySqlDataLayer(host, user, password);
        appServices = new DefaultAppServices(dataLayer);
    }

    public void startConsoleUI() {
          SimpleConsoleUI simpleConsoleUI = new SimpleConsoleUI(this.appServices);
          simpleConsoleUI.start();
    }

    public void startJavaFxGUI() {
        LOGGER.debug("launching gui application");
        launch(JavaFxApplication.class);
    }

    public AppServices getAppServices() {
        return appServices;
    }

    public boolean establishConnection() {
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


    public static void main(String[] args) {
        ApplicationInitializer initializer = ApplicationInitializer.getInstance();
        if (args.length == 3) {
            initializer.setHostInformation(args[0], args[1], args[2]);
            if (initializer.establishConnection()) {
                initializer.startConsoleUI();
            }
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

                initializer.setHostInformation(hostProp, userProp, passwordProp);
                if (initializer.establishConnection()) {
                    initializer.startJavaFxGUI();
                }
            } catch (IOException e) {
                LOGGER.error("Error while loading / reading properties file: " + e.getMessage());
            }
        }
    }
}
