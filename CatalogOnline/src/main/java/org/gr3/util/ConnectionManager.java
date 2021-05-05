package org.gr3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager connectionManager;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private ConnectionManager() {
        try {
            connection = DriverManager.getConnection("");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getConnectionManagerInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }

        return connectionManager;
    }
}
