package com.napier.sem.datalayer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * This implementation of the <code>DataLayer</code> provides
 * access to a MySQL database which connection details are
 * provided by a file called <em>connection.properties</em>.
 */
public class MySqlDataLayer implements DataLayer {

    private static final Logger LOGGER = LogManager.getLogger(MySqlDataLayer.class);

    // connection properties
    private String host;
    private String user;
    private String password;

    private Connection connection;

    /**
     * This constructor simply loads the connection details
     * from the connection.properties file.
     * No connection will be set up at this point!
     */
    public MySqlDataLayer() {
        Properties connectionProps = new Properties();
        try {
            LOGGER.debug("Loading connection properties file");

            // read connection properties from file
            connectionProps.load(getClass().getResourceAsStream("/connection.properties"));

            // read properties
            this.host = connectionProps.getProperty("db_host");
            this.user = connectionProps.getProperty("db_user");
            this.password = connectionProps.getProperty("db_password");

            LOGGER.debug("Successfully read connection properties");
        } catch (IOException e) {
            LOGGER.error("Error while loading / reading properties file: " + e.getMessage());
        }
    }

    @Override
    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(this.host, this.user, this.password);
        LOGGER.debug("Successfully established connection to host");
    }


    @Override
    public ResultSet executeNativeQuery(String sql) throws SQLException {
        if (sql == null || sql.isEmpty()) {
            throw new IllegalArgumentException("Specified sql statement must not be null or empty");
        }
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public void disconnect() throws SQLException {
        if (this.connection == null) {
            LOGGER.debug("There is no current connection to close.");
            return;
        }
        // close connection to host and set variable to null
        this.connection.close();
        this.connection = null;
        LOGGER.debug("Connection closed!");
    }
}
