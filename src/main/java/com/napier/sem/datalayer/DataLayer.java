package com.napier.sem.datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The <code>DataLayer</code> interface provides methods for communication
 * and interaction with a system that provides data for the application.<br/>
 *
 * E.g. it provides a <code>connect()</code> method for establishing a connection
 * such as a <code>disconnect()</code> method for closing the connection.<br/>
 *
 * For setting up native sql queries on the host system, use the <code>executeNativeQuery()</code>
 * method.
 */
public interface DataLayer {

    /**
     * Establishes the connection to a host system.
     * The implementing class needs to care about how the connection is established.
     * @throws SQLException if connection could not be established
     */
    void connect() throws SQLException;

    /**
     * Executes the specified sql query on the connection.
     * This only works for sql-based connections.
     * @param sql the sql query to execute
     * @return the ResultSet for the specified query
     * @throws SQLException if query could not be executed successfully
     */
    ResultSet executeNativeQuery(String sql) throws SQLException;

    /**
     * Closes the connection. After calling this method, it is not possible anymore to
     * call host specific methods such as <code>executeNativeQuery()</code>.
     * @throws SQLException if connection could not be closed
     */
    void disconnect() throws SQLException;
}
