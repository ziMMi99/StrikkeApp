package com.strikkeapp.strikkeapp.data;

import java.sql.*;

public class DataHandler {

    private Connection connection;

    public DataHandler() {
        loadJdbcDriver();
        openConnection();
    }

    private void loadJdbcDriver() {
        try {
            System.out.println("Loading JDBC driver...");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("JDBC driver loaded");

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
        }
    }

    private void openConnection() {
        try {
            String connectionString =
                    "jdbc:sqlserver://localhost:1433;" +
                            "instanceName=SQLEXPRESS;" +
                            "databaseName=" + "Strik_DB" + ";" +
                            "integratedSecurity=true;" +
                            "trustServerCertificate=true;";

            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

        } catch (SQLException e) {

            System.out.println("Could not connect to database: " + "Strik_DB");
            System.out.println("Could not connect to the database: " + "Strik_DB");

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
