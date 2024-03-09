package com.strikkeapp.strikkeapp.data;

import java.sql.*;

public class DataHandler {

    private Connection connection;

    public DataHandler() {
        openConnection("Strik_DB");
    }

    private void openConnection(String databaseName) {
        try {
            String connectionString =
                    "jdbc:sqlserver://localhost:1433;" +
                            "instanceName=SQLEXPRESS;" +
                            "databaseName=" + databaseName + ";" +
                            "integratedSecurity=true;" +
                            "trustServerCertificate=true;";

            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

        } catch (SQLException e) {

            System.out.println("Could not connect to database: " + databaseName);
            System.out.println("Could not connect to the database: " + databaseName);

            System.out.println(e.getMessage());
        }
    }

    /**
     * Makes call to db
     *
     * @param procedure - stored procedure
     * @return - callablestatement
     */
    public CallableStatement makeCall(String procedure) {
        try  {
            return connection.prepareCall(procedure);
        } catch (SQLException e) {
            System.out.println("Could not create callable statement - " + e.getMessage());
            return null;
        }
    }
}
